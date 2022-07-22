package com.balance.balancemanager.dao.category;

import com.balance.balancemanager.entity.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{

    private EntityManager entityManager;

    @Autowired
    public CategoryDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Category> findAll() {
        Session currentSession = getCurrentSession();

        Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);

        return theQuery.getResultList();
    }

    @Override
    public Category findById(int theId) {
        Session currentSession = getCurrentSession();

        return currentSession.get(Category.class, theId);
    }

    @Override
    public void save(Category category) {
        Session currentSession = getCurrentSession();

        currentSession.saveOrUpdate(category);
    }

    @Override
    public void deleteById(int theId) {
        Session currentSession = getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Category " +
                "where id=:categoryId");
        theQuery.setParameter("categoryId", theId);

        theQuery.executeUpdate();
    }
}
