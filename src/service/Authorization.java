package service;

import entity.ListUsers;
import entity.User;
import entity.enums.UserRole;

public class Authorization {
    private ListUsers listUsers;

    public Authorization(ListUsers listUsers) {
        this.listUsers = listUsers;
    }

    public User auth(String login, String password) {
        for(User user : listUsers.getUserList()) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;

    }

    public UserRole authorize(User user) {
        return user.getUserRole();
    }

}
