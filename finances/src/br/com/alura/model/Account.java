package br.com.alura.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String owner;

    private String number;

    private String bank;

    private String agency;

    @OneToMany(mappedBy = "account")
    private List<Movimentation> movimentations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public List<Movimentation> getMovimentations() {
        return movimentations;
    }

    public void setMovimentations(List<Movimentation> movimentations) {
        this.movimentations = movimentations;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", number='" + number + '\'' +
                ", bank='" + bank + '\'' +
                ", agency='" + agency + '\'' +
                ", movimentationsSize=" + movimentations.size() +
                '}';
    }
}
