package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.model.Client;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;

public class AccountClientTest {

    public static void main(String[] args) {
        Client client = new Client();
        client.setName("Leonardo");
        client.setAddress("Rua Fulano, 123");
        client.setProfession("Professor");

        Client client2 = new Client();
        client2.setName("Douglas");
        client2.setAddress("Rua Fulano, 234");
        client2.setProfession("Professor");

        Account account = new Account();
        account.setId(2);

        client.setAccount(account);
        client2.setAccount(account);

        EntityManager em = JPAUtils.createEntityManager();
        em.getTransaction().begin();

        em.persist(client);
        em.persist(client2);

        em.getTransaction().commit();
        em.close();
    }

}
