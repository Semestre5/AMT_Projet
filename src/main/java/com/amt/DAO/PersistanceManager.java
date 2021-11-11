package com.amt.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistanceManager {
    private static EntityManagerFactory emFactory;

    public static EntityManager getEntityManager() {
        if (emFactory == null){
            EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("persistant-unit-factory");
        }
        return emFactory.createEntityManager();
    }
    public static EntityManager beginTransaction() {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }
    public static void commitTransaction(EntityManager em) {
        em.close();
    }
}
