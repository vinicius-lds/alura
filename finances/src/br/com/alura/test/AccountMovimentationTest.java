package br.com.alura.test;

import br.com.alura.model.Movimentation;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;

public class AccountMovimentationTest {

    public static void main(String[] args) {

        EntityManager em = JPAUtils.createEntityManager();
        em.getTransaction().begin();

        Movimentation movimentation = em.find(Movimentation.class, 3);
        System.out.println(movimentation.getAccount().getMovimentations().size());

        em.getTransaction().commit();
        em.close();

    }

}
