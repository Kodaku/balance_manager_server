package com.balance.balancemanager.dao.balance;

import com.balance.balancemanager.entity.Balance;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BalanceDAOImpl implements BalanceDAO{

    private final EntityManager entityManager;

    public BalanceDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }


    @Override
    public List<Balance> findAll() {
        Session currentSession = getCurrentSession();
        Query<Balance> theQuery = currentSession.createQuery("from Balance", Balance.class);
        return theQuery.getResultList();
    }

    @Override
    public Balance findById(int theId) {
        Session currentSession = getCurrentSession();
        return currentSession.get(Balance.class, theId);
    }

    @Override
    public void save(Balance balance) {
        Session currentSession = getCurrentSession();

        currentSession.saveOrUpdate(balance);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = getCurrentSession();
        Query theQuery = currentSession.createQuery("delete from Balance " +
                "where id=:balanceId");

        theQuery.setParameter("balanceId", theId);

        theQuery.executeUpdate();
    }
}
