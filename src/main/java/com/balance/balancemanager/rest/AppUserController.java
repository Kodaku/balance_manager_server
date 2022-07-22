package com.balance.balancemanager.rest;

import com.balance.balancemanager.entity.AppUser;
import com.balance.balancemanager.error.exceptions.ResourceNotFoundException;
import com.balance.balancemanager.service.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    private AppUser getAppUser(int userId) {
        AppUser appUser = appUserService.findById(userId);

        if (appUser == null) {
            throw new ResourceNotFoundException("User not found with ID " + userId);
        }

        return appUser;
    }

    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<AppUser> getUsers() {
        return appUserService.findAll();
    }

    @GetMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppUser getUser(@PathVariable int userId) {
        return getAppUser(userId);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED) // 201
    @CrossOrigin(origins = "http://localhost:3000")
    public AppUser addUser(@RequestBody AppUser appUser) {
        appUser.setId(0);

        appUserService.save(appUser);

        return appUser;
    }

    @PutMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppUser updateUser(@RequestBody AppUser appUser, @PathVariable int userId) {
        AppUser currentAppUser = getAppUser(userId);

        currentAppUser.setName(appUser.getName());

        appUserService.save(currentAppUser);

        return currentAppUser;
    }

    @DeleteMapping("/users/{userId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteUser(@PathVariable int userId) {
        // just check if the user exists
        getAppUser(userId);

        appUserService.deleteById(userId);

        return "Deleted user with id: " + userId;
    }
}
