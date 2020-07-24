package com.web.project.jobtracker.usermanagement;

public interface IUserManagerPersistence {

    public User getUser(String email);

    public void updateUser(User user);
}
