package com.web.project.jobtracker.usermanagement;

/**
 * @author Parth Bagaria
 *
 * Banner ID: B00839783
 */
public interface IUserManagerPersistence {

    public User getUser(String email);

    public void updateUser(User user);

    public void newUser(NewUser user);

    public void resetPassword(String email);
}
