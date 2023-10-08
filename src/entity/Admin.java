package entity;

import entity.enums.UserRole;

public class Admin extends User{

    private ListUsers listUsers;

    public Admin(String fullName, String login, String password, int age,int balance, UserRole userRole,
                 ListTransaction listTransaction, ListUsers listUsers) {
        super(fullName, login, password, age,balance, userRole, listTransaction);
        this.listUsers = listUsers;
    }

    public Admin(String fullName, String login, String password, int age,int balance, UserRole userRole,
                 ListTransaction listTransaction) {
        super(fullName, login, password, age,balance, userRole, listTransaction);
    }

    public Admin(User user) {
        super(user.getFullName(), user.getLogin(), user.getPassword(), user.getAge(), user.getBalance(), user.getUserRole(), user.getListTransaction());
    }

    public void updateAdmin(String fullName, String login, String password, int age) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.age = age;
    }


    public ListUsers getListUsers() {
        return listUsers;
    }

    public void setListUsers(ListUsers listUsers) {
        this.listUsers = listUsers;
    }
}
