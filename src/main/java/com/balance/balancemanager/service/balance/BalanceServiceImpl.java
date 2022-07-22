package com.balance.balancemanager.service.balance;

import com.balance.balancemanager.dao.balance.BalanceDAO;
import com.balance.balancemanager.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService{

    private BalanceDAO balanceDAO;

    @Autowired
    public BalanceServiceImpl(BalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    @Override
    @Transactional
    public List<Balance> findAll() {
        return balanceDAO.findAll();
    }

    @Override
    @Transactional
    public Balance findById(int theId) {
        return balanceDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(Balance balance) {
        balanceDAO.save(balance);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        balanceDAO.deleteById(theId);
    }
}
