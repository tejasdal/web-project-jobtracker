package com.web.project.jobtracker.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerService implements IUserManagerService{

    @Autowired
    private IUserManagerPersistence userManagerPersistence;

    @Override
    public User getUser(String email) {
        return userManagerPersistence.getUser(email);
    }

    @Override
    public void updateUser(User user) {
        userManagerPersistence.updateUser(user);
    }
}
