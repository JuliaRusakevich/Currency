package com.gmail.juliarusakevich.currency.dao;

import com.gmail.juliarusakevich.currency.dao.api.ICrudDao;
import com.gmail.juliarusakevich.currency.dao.exception.DAOException;
import com.gmail.juliarusakevich.currency.dao.model.Currency;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

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
public class CurrencyDao implements ICrudDao<Currency, Integer> {

    private static final CurrencyDao INSTANCE = new CurrencyDao();

    private CurrencyDao() {
    }

    /**
     * Connection factory to database.
     * <p>
     * private static final EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("com.gmail.juliarusakevich.currency.dao.model");
     */
    @Override
    public void create(Currency entity) throws DAOException {
        EntityManagerFactory sessionFactory =
                Persistence.createEntityManagerFactory(
                        "com.gmail.juliarusakevich.currency.dao.model");

        EntityManager entityManager = sessionFactory.createEntityManager();
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
        EntityManagerFactory sessionFactory =
                Persistence.createEntityManagerFactory(
                        "com.gmail.juliarusakevich.currency.dao.model");

        EntityManager entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Currency.class);
            var root = criteriaQuery.from(Currency.class);
            criteriaQuery.select(root);
            var query = entityManager.createQuery(criteriaQuery);
            entityManager.getTransaction().commit();

            return query.getResultList().stream()
                    .sorted(comparing(Currency::getId))
                    .collect(Collectors.toList());
        } catch (IllegalStateException e) {
            entityManager.getTransaction().rollback();
            throw new DAOException("Unable to return a list");
        }
    }

    @Override
    public Optional<Currency> readById(Integer id) throws DAOException {
        EntityManagerFactory sessionFactory =
                Persistence.createEntityManagerFactory(
                        "com.gmail.juliarusakevich.currency.dao.model");

        EntityManager entityManager = sessionFactory.createEntityManager();
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
    public void update(Currency oldEntity, Currency newEntity) throws DAOException {
        EntityManagerFactory sessionFactory =
                Persistence.createEntityManagerFactory(
                        "com.gmail.juliarusakevich.currency.dao.model");

        var entityManager = sessionFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            var criteriaBuilder = entityManager.getCriteriaBuilder();
            // create update
            var update = criteriaBuilder.createCriteriaUpdate(Currency.class);
            // set the root class
            var root = update.from(Currency.class);
            // set update and where clause
            setNewDate(oldEntity, newEntity, criteriaBuilder, update, root);
            LockModeType optimistic = LockModeType.OPTIMISTIC;
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

    @Override
    public void delete(Integer id) throws DAOException {
        EntityManagerFactory sessionFactory =
                Persistence.createEntityManagerFactory(
                        "com.gmail.juliarusakevich.currency.dao.model");

        EntityManager entityManager = sessionFactory.createEntityManager();
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

    public static CurrencyDao getInstance() {
        return INSTANCE;
    }

}
