package entity;

import entity.User;
import entity.enums.TypeTransaction;

import java.util.Date;
import java.util.List;

public class Transaction {

    private User user;
    private User transUser;
    private TypeTransaction typeTransaction;
    private int sumTrans;
    private Date date;


    public Transaction(User user, User transUser, TypeTransaction typeTransaction, int sumTrans, Date date) {
        this.user = user;
        this.transUser = transUser;
        this.typeTransaction = typeTransaction;
        this.sumTrans = sumTrans;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if(user == null) {
            System.out.println("Данного пользователя не существует!");
        } else {
            this.user = user;
        }

    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransaction typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public int getSumTrans() {
        return sumTrans;
    }

    public void setSumTrans(int sumTrans) {
        if(sumTrans <= 0) {
            System.out.println("Сумма перевода не может равняться или быть меньше нуля!");
        } else {
            this.sumTrans = sumTrans;
        }

    }

    public User getTransUser() {
        return transUser;
    }

    public void setTransUser(User transUser) {
        this.transUser = transUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Транзакция (" +
                "пользователь: " + user.getFullName() + ";" +
                " кому: " + transUser.getFullName() + ";" +
                " тип транзакции: " + typeTransaction.toString() + ";" +
                " сумма транзакции: " + sumTrans + ";" +
                " дата транзакции: " + date + ")\n";
    }
}

