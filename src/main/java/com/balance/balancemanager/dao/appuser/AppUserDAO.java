package com.balance.balancemanager.dao.appuser;

import com.balance.balancemanager.entity.AppUser;

import java.util.List;

public interface AppUserDAO {

    public List<AppUser> findAll();

    public AppUser findById(int theId);

    public void save(AppUser appUser);

    public void deleteById(int theId);
}
