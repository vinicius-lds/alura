package br.com.alura.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtils {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("finances");

    private JPAUtils() {
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
