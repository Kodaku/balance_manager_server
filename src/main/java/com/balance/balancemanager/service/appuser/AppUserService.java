package com.balance.balancemanager.service.appuser;

import com.balance.balancemanager.entity.AppUser;

import java.util.List;

public interface AppUserService {

    public List<AppUser> findAll();

    public AppUser findById(int theId);

    public void save(AppUser appUser);

    public void deleteById(int theId);
}
