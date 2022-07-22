package com.balance.balancemanager.dao.appuser;

import com.balance.balancemanager.entity.AppUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AppUserDAOImpl implements AppUserDAO{

    private final EntityManager entityManager;

    @Autowired
    public AppUserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AppUser> findAll() {

        Session currentSession = getCurrentSession();

        Query<AppUser> theQuery = currentSession.createQuery("from AppUser", AppUser.class);

        return theQuery.getResultList();
    }

    private Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public AppUser findById(int theId) {
        Session currentSession = getCurrentSession();

        return currentSession.get(AppUser.class, theId);
    }

    @Override
    public void save(AppUser appUser) {
        Session currentSession = getCurrentSession();

        currentSession.saveOrUpdate(appUser);
    }

    @Override
    public void deleteById(int theId) {

        Session currentSession = getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from AppUser where " +
                "id=:userId");
        theQuery.setParameter("userId", theId);

        theQuery.executeUpdate();
    }
}
