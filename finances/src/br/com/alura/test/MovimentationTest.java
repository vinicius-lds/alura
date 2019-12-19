package br.com.alura.test;

import br.com.alura.model.Account;
import br.com.alura.model.Category;
import br.com.alura.model.Movimentation;
import br.com.alura.model.MovimentationType;
import br.com.alura.util.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

public class MovimentationTest {

    public static void main(String[] args) {

        Category category1 = new Category("Viagem");
        Category category2 = new Category("Negócios");

        Account account = new Account();
        account.setId(2);

        Movimentation movimentation1 = new Movimentation();
        movimentation1.setDate(Calendar.getInstance());
        movimentation1.setDescription("Viagem à SP");
        movimentation1.setMovimentationType(MovimentationType.OUT);
        movimentation1.setValue(new BigDecimal("100.0"));
        movimentation1.setCategories(Arrays.asList(category1, category2));

        movimentation1.setAccount(account);

        Movimentation movimentation2 = new Movimentation();
        movimentation2.setDate(Calendar.getInstance());
        movimentation2.setDescription("Viagem ao RJ");
        movimentation2.setMovimentationType(MovimentationType.OUT);
        movimentation2.setValue(new BigDecimal("300.0"));
        movimentation2.setCategories(Arrays.asList(category1, category2));

        movimentation2.setAccount(account);

        EntityManager em = JPAUtils.createEntityManager();
        em.getTransaction().begin();

        em.persist(category1);
        em.persist(category2);
        em.persist(movimentation1);
        em.persist(movimentation2);

        em.getTransaction().commit();
        em.close();

    }

}
