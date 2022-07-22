package com.balance.balancemanager.service.expense;

import com.balance.balancemanager.dao.expense.ExpenseDAO;
import com.balance.balancemanager.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    @Autowired
    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @Override
    @Transactional
    public List<Expense> findAll() {
        return expenseDAO.findAll();
    }

    @Override
    @Transactional
    public Expense findById(int theId) {
        return expenseDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Expense expense) {
        expenseDAO.save(expense);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        expenseDAO.deleteById(theId);
    }
}
