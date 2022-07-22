package com.balance.balancemanager.service.balance;

import com.balance.balancemanager.entity.Balance;

import java.util.List;

public interface BalanceService {

    public List<Balance> findAll();

    public Balance findById(int theId);

    public void save(Balance balance);

    public void deleteById(int theId);
}
