package com.web.project.jobtracker.usermanagement;

public interface IUserManagerService {

    public User getUser(String email);

    public void updateUser(User user);
}
