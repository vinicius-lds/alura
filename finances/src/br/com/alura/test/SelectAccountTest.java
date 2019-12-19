package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;

public class SelectAccountTest {

    public static void main(String[] args) {
        EntityManager entityManager = JPAUtils.createEntityManager();
        entityManager.getTransaction().begin();

        Account account = entityManager.find(Account.class, 1);
        System.out.println(account);
        account.setNumber("789");
        System.out.println(account);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
