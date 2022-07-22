package com.balance.balancemanager.service.appuser;

import com.balance.balancemanager.dao.appuser.AppUserDAO;
import com.balance.balancemanager.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService{

    private AppUserDAO appUserDAO;

    @Autowired
    public AppUserServiceImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    @Transactional
    public List<AppUser> findAll() {
        return appUserDAO.findAll();
    }

    @Override
    @Transactional
    public AppUser findById(int theId) {
        return appUserDAO.findById(theId);
    }

    @Override
    @Transactional
    public void save(AppUser appUser) {
        appUserDAO.save(appUser);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        appUserDAO.deleteById(theId);
    }
}
