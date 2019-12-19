package br.com.alura.util;

import br.com.alura.model.Account;
import br.com.alura.model.Movimentation;
import br.com.alura.model.MovimentationType;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Calendar;

public class MovimentationUtils {

    public static void doIt() {
        EntityManager manager = JPAUtils.createEntityManager();

        manager.getTransaction().begin();

        Account account1 = manager.find(Account.class, 1);
        Account account2 = manager.find(Account.class, 2);
        Account account3 = manager.find(Account.class, 3);
        Account account4 = manager.find(Account.class, 4);
        Account account5 = manager.find(Account.class, 5);


        Movimentation movimentation1 = new Movimentation();
        Movimentation movimentation2 = new Movimentation();
        Movimentation movimentation3 = new Movimentation();
        Movimentation movimentation4 = new Movimentation();

        movimentation1.setDate(Calendar.getInstance());
        movimentation1.setDescription("Account de luz - ABRIL/2012");
        movimentation1.setValue(new BigDecimal("135"));
        movimentation1.setMovimentationType(MovimentationType.OUT);
        movimentation1.setAccount(account1);

        manager.persist(movimentation1);

        movimentation2.setDate(Calendar.getInstance());
        movimentation2.setDescription("Almoco no Restaurante - AGOSTO/2012");
        movimentation2.setValue(new BigDecimal("175.80"));
        movimentation2.setMovimentationType(MovimentationType.OUT);
        movimentation2.setAccount(account1);

        manager.persist(movimentation2);

        movimentation3.setDate(Calendar.getInstance());
        movimentation3.setDescription("Aluguel - MAIO/2012");
        movimentation3.setValue(new BigDecimal("680.00"));
        movimentation3.setMovimentationType(MovimentationType.IN);
        movimentation3.setAccount(account1);

        manager.persist(movimentation3);

        movimentation4.setDate(Calendar.getInstance());
        movimentation4.setDescription("Salario - FEVEREIRO/2012");
        movimentation4.setValue(new BigDecimal("3830.68"));
        movimentation4.setMovimentationType(MovimentationType.IN);
        movimentation4.setAccount(account1);

        manager.persist(movimentation4);

        Movimentation movimentation5 = new Movimentation();
        Movimentation movimentation6 = new Movimentation();

        movimentation5.setDate(Calendar.getInstance());
        movimentation5.setDescription("Account de telefone - SETEMBRO/2011");
        movimentation5.setValue(new BigDecimal("168.27"));
        movimentation5.setMovimentationType(MovimentationType.OUT);
        movimentation5.setAccount(account2);

        manager.persist(movimentation5);

        movimentation6.setDate(Calendar.getInstance());
        movimentation6.setDescription("Aniversario - MAIO/2011");
        movimentation6.setValue(new BigDecimal("200"));
        movimentation6.setMovimentationType(MovimentationType.IN);
        movimentation6.setAccount(account2);

        manager.persist(movimentation6);

        Movimentation movimentation7 = new Movimentation();
        Movimentation movimentation8 = new Movimentation();
        Movimentation movimentation9 = new Movimentation();

        movimentation7.setDate(Calendar.getInstance());
        movimentation7.setDescription("Lanche - JULHO/2011");
        movimentation7.setValue(new BigDecimal("28.50"));
        movimentation7.setMovimentationType(MovimentationType.OUT);
        movimentation7.setAccount(account3);

        manager.persist(movimentation7);

        movimentation8.setDate(Calendar.getInstance());
        movimentation8.setDescription("Presente - DEZEMBRO/2011");
        movimentation8.setValue(new BigDecimal("49.99"));
        movimentation8.setMovimentationType(MovimentationType.OUT);
        movimentation8.setAccount(account3);

        manager.persist(movimentation8);

        movimentation9.setDate(Calendar.getInstance());
        movimentation9.setDescription("Bonus - JANEIRO/2012");
        movimentation9.setValue(new BigDecimal("2000"));
        movimentation9.setMovimentationType(MovimentationType.IN);
        movimentation9.setAccount(account3);

        manager.persist(movimentation9);

        Movimentation movimentation10 = new Movimentation();

        movimentation10.setDate(Calendar.getInstance());
        movimentation10.setDescription("Carnaval - MARCO/2012");
        movimentation10.setValue(new BigDecimal("765.20"));
        movimentation10.setMovimentationType(MovimentationType.OUT);
        movimentation10.setAccount(account4);

        manager.persist(movimentation10);

        Movimentation movimentation11 = new Movimentation();
        Movimentation movimentation12 = new Movimentation();

        movimentation11.setDate(Calendar.getInstance());
        movimentation11.setDescription("Salario - ABRIL/2012");
        movimentation11.setValue(new BigDecimal("2651.90"));
        movimentation11.setMovimentationType(MovimentationType.IN);
        movimentation11.setAccount(account5);

        manager.persist(movimentation11);

        movimentation12.setDate(Calendar.getInstance());
        movimentation12.setDescription("Bonus - JANEIRO/2012");
        movimentation12.setValue(new BigDecimal("1000"));
        movimentation12.setMovimentationType(MovimentationType.IN);
        movimentation12.setAccount(account5);

        manager.persist(movimentation12);

        manager.getTransaction().commit();

        manager.close();


    }

}
