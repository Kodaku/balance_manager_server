package com.balance.balancemanager.dao.expense;

import com.balance.balancemanager.entity.Expense;

import java.util.List;

public interface ExpenseDAO {

    public List<Expense> findAll();

    public Expense findById(int theId);

    public void save(Expense expense);

    public void deleteById(int theId);
}
