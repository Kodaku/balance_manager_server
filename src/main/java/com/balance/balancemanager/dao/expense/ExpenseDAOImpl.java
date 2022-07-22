package com.balance.balancemanager.dao.expense;

import com.balance.balancemanager.entity.Expense;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO{

    private EntityManager entityManager;

    @Autowired
    public ExpenseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Expense> findAll() {
        Session currentSession = getCurrentSession();
        Query<Expense> theQuery = currentSession.createQuery("from Expense", Expense.class);
        return theQuery.getResultList();
    }

    @Override
    public Expense findById(int theId) {
        Session currentSession = getCurrentSession();
        return currentSession.get(Expense.class, theId);
    }

    @Override
    public void save(Expense expense) {
        Session currentSession = getCurrentSession();

        currentSession.saveOrUpdate(expense);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Expense " +
                "where id=:expenseId");
        theQuery.setParameter("expenseId", theId);

        theQuery.executeUpdate();
    }
}
