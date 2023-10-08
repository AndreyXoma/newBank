package entity;

import entity.enums.UserRole;

public class User {

    protected String fullName;
    protected String login;
    protected String password;
    protected int age;
    protected int balance;
    protected UserRole userRole;
    protected ListTransaction listTransaction;

    public User(String fullName, String login, String password, int age,int balance, UserRole userRole, ListTransaction listTransaction) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.age = age;
        this.balance = balance;
        this.userRole = userRole;
        this.listTransaction = listTransaction;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        if(balance < 0) {
            System.out.println("Баланс не может быть меньше нуля!");
        } else {
            this.balance = balance;
        }

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if(!fullName.equals(null)) {
            this.fullName = fullName;
        } else {
            System.out.println("Введите корректные данные ФИО");
        }

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if(!login.equals(null)) {
            this.login = login;
        } else {
            System.out.println("Введите корректные данные логина!");
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(!password.equals(null)) {
            this.password = password;
        } else {
            System.out.println("Введите корректные данные пароля!");
        }

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age <= 0) {
            System.out.println("Введите корректный возраст!");
        } else {
            this.age = age;
        }


    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public ListTransaction getListTransaction() {
        return listTransaction;
    }

    public void setListTransaction(ListTransaction listTransaction) {
        this.listTransaction = listTransaction;
    }

    @Override
    public String toString() {
        return "Пользователь: (" +
                "имя: " + fullName + "," +
                " логин: " + login + "," +
                " пароль: " + password + "," +
                " возраст: " + age + "," +
                " баланс: " + balance + "," +
                " роль: " + userRole.toString() + "," +
                " транзакции: " + listTransaction + ");\n";
    }

    public void updateBalance(int balance) {
        this.balance = balance;
    }
}
