package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.model.Movimentation;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class InsertMovimentationTest {

    public static void main(String[] args) {

        EntityManager em = JPAUtils.createEntityManager();

        Query query = em.createQuery("SELECT a FROM Account a JOIN FETCH a.movimentations WHERE a.id = :pId");
        query.setParameter("pId", 1);
        Account account = (Account) query.getSingleResult();

        List<Movimentation> movimentations = account.getMovimentations();

        em.close();

        movimentations.forEach(movimentation -> System.out.println(movimentation.getDescription()));

    }

}
