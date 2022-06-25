package spring.currency.dao;

import jakarta.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import spring.currency.dao.api.ICurrencyDao;
import spring.currency.dao.connection.api.IEntityManager;
import spring.currency.dao.exception.DAOException;
import spring.currency.dao.model.Currency;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Optional.ofNullable;

/*
Let's look at a simple criteria query that will retrieve all the rows of “ITEM” from the database:

Create an instance of Session from the SessionFactory object
Create an instance of CriteriaBuilder by calling the getCriteriaBuilder() method
Create an instance of CriteriaQuery by calling the CriteriaBuilder createQuery() method
Create an instance of Query by calling the Session createQuery() method
Call the getResultList() method of the query object, which gives us the results
 */
@Repository
public class CurrencyDao implements ICurrencyDao {

    private final IEntityManager manager;

    public CurrencyDao(IEntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Currency entity) throws DAOException {
        var entityManager = manager.getManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Can't create an entity.");
        }
    }

    @Override
    public List<Currency> read() throws DAOException {

        var entityManager = manager.getManager();
        try {
            entityManager.getTransaction().begin();

            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Currency.class);
            var root = criteriaQuery.from(Currency.class);
            criteriaQuery.select(root);
            var query = entityManager.createQuery(criteriaQuery).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

            return query;
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Unable to return a list");
        }
    }

    @Override
    public Optional<Currency> readById(Integer id) throws DAOException {
        var entityManager = manager.getManager();
        try {
            entityManager.getTransaction().begin();
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Currency.class);
            var root = criteriaQuery.from(Currency.class);
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
            var query = entityManager.createQuery(criteriaQuery);
            var result = query.getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
            return ofNullable(result);
        } catch (NoResultException nre) {
            throw new DAOException("Currency was not found");
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Unable to return");
        }

    }

    @Override
    public void update(Integer id, LocalDateTime dtUpdate, Currency entity) throws DAOException {
        var entityManager = manager.getManager();
        entityManager.getTransaction().begin();

        var currencyFromDB = entityManager.find(Currency.class, id);

        /*
        1 проверка совпадает ли dtUpdate переданный, с тем, который в базе,
        если не совпадает, то дальше идти смысла нет

        2 проверка - на базе
         */
        if (currencyFromDB.getUpdateDate().equals(dtUpdate)) {
            throw new IllegalArgumentException("Валюта уже была обновлена кем-то ранее!");
        }

        currencyFromDB.setCode(entity.getCode());
        currencyFromDB.setDescription(entity.getDescription());
        currencyFromDB.setTitle(entity.getTitle());

        entityManager.persist(currencyFromDB);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    @Override
    public void delete(Integer id) throws DAOException {
        var entityManager = manager.getManager();
        try {
            entityManager.getTransaction().begin();
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            // create delete
            var criteriaDelete = criteriaBuilder.createCriteriaDelete(Currency.class);
            // set the root class
            var root = criteriaDelete.from(Currency.class);
            // set where clause
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            // perform update
            var row = entityManager.createQuery(criteriaDelete).executeUpdate();
            if (row == 0) {
                throw new DAOException("Currency for removing was not found");
            } else {
                entityManager.getTransaction().commit();
                entityManager.close();
            }
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Unable to delete");
        }
    }

}


/*
    @Override
    public void update(Currency oldEntity, Currency newEntity) throws DAOException {
        var entityManager = manager.getManager();
        try {
            entityManager.getTransaction().begin();
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            // create update
            var update = criteriaBuilder.createCriteriaUpdate(Currency.class);
            // set the root class
            var root = update.from(Currency.class);
            // set update and where clause
            setNewDate(oldEntity, newEntity, criteriaBuilder, update, root);
            // LockModeType optimistic = LockModeType.OPTIMISTIC;???????????????????????????
            update.where(criteriaBuilder.equal(root.get("id"), oldEntity.getId()));
            // perform update
            var result = entityManager.createQuery(update).executeUpdate();
            if (result == 0) {
                throw new DAOException("Currency ID wasn't found");
            }
            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Unable to update");
        }
    }

        private void setNewDate(Currency oldEntity,
                            Currency newEntity,
                            CriteriaBuilder criteriaBuilder,
                            CriteriaUpdate<Currency> update,
                            Root<Currency> root) {

        update.set("title", newEntity.getTitle());
        update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("title"), oldEntity.getTitle()));
        update.set("description", newEntity.getDescription());
        update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("description"), oldEntity.getDescription()));
        update.set("code", newEntity.getCode());
        update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("code"), oldEntity.getCode()));
        update.set("updateDate", newEntity.getUpdateDate());
        update.where(criteriaBuilder.greaterThanOrEqualTo(root.get("updateDate"), oldEntity.getUpdateDate()));
    }
 */


/*

Работает, возвращает без сортировки
    @Override
    public List<Currency> read() throws DAOException {
        var entityManager = manager.getManager();
            entityManager.getTransaction().begin();
            var result = entityManager.createQuery("from Currency", Currency.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return result;
    }
 */