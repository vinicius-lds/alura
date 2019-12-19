package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;

public class CreateAccountTest {

    public static void main(String[] args) {

        Account account = new Account();
        account.setOwner("Vinicius");
        account.setBank("Nubank");
        account.setAgency("123");
        account.setNumber("456");

        EntityManager entityManager = JPAUtils.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

}
