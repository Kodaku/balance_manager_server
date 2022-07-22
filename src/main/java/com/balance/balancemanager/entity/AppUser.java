package com.balance.balancemanager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private List<Balance> balances;

    public AppUser() {}

    public AppUser(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public void addBalance(Balance balance) {
        if(balances == null) {
            balances = new ArrayList<Balance>();
        }
        balances.add(balance);
        balance.setUserId(this.getId());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balances=" + balances +
                '}';
    }
}
