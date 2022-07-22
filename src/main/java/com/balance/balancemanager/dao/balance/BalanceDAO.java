package com.balance.balancemanager.dao.balance;

import com.balance.balancemanager.entity.Balance;

import java.util.List;

public interface BalanceDAO {
    public List<Balance> findAll();

    public Balance findById(int theId);

    public void save(Balance balance);

    public void deleteById(int theId);
}
