package com.balance.balancemanager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="user_id")
    private int userId;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="balance_id")
    private List<Expense> expenses;

    public Balance() {}

    public Balance(String name) {
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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void addExpense(Expense expense) {
        if (expenses == null) {
            expenses = new ArrayList<Expense>();
        }
        expenses.add(expense);
        expense.setBalanceId(this.getId());
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
