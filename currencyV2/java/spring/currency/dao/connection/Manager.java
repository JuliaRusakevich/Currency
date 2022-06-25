package spring.currency.dao.connection;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;
import spring.currency.dao.connection.api.IEntityManager;

@Repository
public class Manager implements IEntityManager {

    private final EntityManagerFactory entityManagerFactory;

    private static final String PERSISTENCE_UNIT_NAME = "spring.currency.dao.model";

    public Manager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(
                PERSISTENCE_UNIT_NAME);
    }

    @Override
    public EntityManager getManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
