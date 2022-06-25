package spring.currency.dao.connection.api;

import jakarta.persistence.EntityManager;

public interface IEntityManager extends AutoCloseable {

    EntityManager getManager();
}
