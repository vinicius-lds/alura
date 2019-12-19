package br.com.alura.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Entity
public class Movimentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private MovimentationType movimentationType;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @ManyToOne
    private Account account;

    @ManyToMany
    private List<Category> categories;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public MovimentationType getMovimentationType() {
        return movimentationType;
    }

    public void setMovimentationType(MovimentationType movimentationType) {
        this.movimentationType = movimentationType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
