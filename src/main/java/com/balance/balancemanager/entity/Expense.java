package com.balance.balancemanager.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="amount")
    private float amount;
    @Column(name="date")
    private Date date;
    @Column(name="description")
    private String description;
    @Column(name="is_debit")
    private boolean isDebit;
    @Column(name="balance_id")
    private int balanceId;
    @Column(name="category_id")
    private int categoryId;

    public Expense() {}

    public Expense(float amount, Date date, String description, boolean isDebit) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.isDebit = isDebit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDebit() {
        return isDebit;
    }

    public void setDebit(boolean debit) {
        isDebit = debit;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", isDebit=" + isDebit +
                ", balanceId=" + balanceId +
                ", categoryId=" + categoryId +
                '}';
    }
}
