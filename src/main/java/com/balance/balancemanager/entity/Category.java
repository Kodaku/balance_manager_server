package com.balance.balancemanager.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
                                                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    private List<Expense> expenses;

    public Category() {}

    public Category(String name) {
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

    public void addExpense(Expense expense) {
        if(expenses == null) {
            expenses = new ArrayList<Expense>();
        }
        expenses.add(expense);
        expense.setCategoryId(this.getId());
    }
}
