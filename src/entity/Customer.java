package entity;

import entity.enums.UserRole;

public class Customer extends User{


    public Customer(String fullName, String login, String password, int age, int balance, UserRole userRole, ListTransaction listTransaction) {
        super(fullName, login, password, age, balance, userRole, listTransaction);
    }

    public Customer(User user) {
        super(user.getFullName(), user.getLogin(), user.getPassword(), user.getAge(), user.getBalance(), user.getUserRole(), user.getListTransaction());
    }

    public void updateCustomer(String fullName, String login, String password, int age) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.age = age;
    }

    public void updateBalance(int balance) {
        this.balance = balance;
    }
}
