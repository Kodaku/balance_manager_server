package com.balance.balancemanager.service.expense;

import com.balance.balancemanager.entity.Expense;

import java.util.List;

public interface ExpenseService {

    public List<Expense> findAll();

    public Expense findById(int theId);

    public void save(Expense expense);

    public void deleteById(int theId);
}
