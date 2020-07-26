package com.web.project.jobtracker.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Parth Bagaria
 *
 * Banner ID: B00839783
 */
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

    @Override
    public void newUser(NewUser user) {
        userManagerPersistence.newUser(user);
    }

    @Override
    public void resetPassword(String email) {
        userManagerPersistence.resetPassword(email);
    }
}
