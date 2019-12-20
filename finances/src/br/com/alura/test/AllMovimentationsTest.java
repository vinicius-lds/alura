package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class AllMovimentationsTest {

    public static void main(String[] args) {

        EntityManager em = JPAUtils.createEntityManager();
        em.getTransaction().begin();

        String jpqlQuery = "SELECT DISTINCT a FROM Account a LEFT JOIN FETCH a.movimentations";
        Query query = em.createQuery(jpqlQuery);
        List<Account> resultList = query.getResultList();
        resultList.forEach(System.out::println);

        em.getTransaction().commit();
        em.close();

    }

}
