package entity;

import java.util.*;
import java.util.stream.Collectors;

public class ListTransaction {

    //добавление транзакции

    private Map<User, List<Transaction>> mapTransaction;
    private List<Transaction> data;
    private int size = 0;

    public ListTransaction(List<Transaction> data) {
        this.data = data;
        mapTransaction = new HashMap<>();
    }

    public void printTrans(User user) {
        System.out.println(mapTransaction.get(user));
    }



    public void createTrans(User user, Transaction transaction) {
        data.add(transaction);
        mapTransaction.put(user, data);
        System.out.println("перевод создан");
    }

    public List<Transaction> getData() {
        return data;
    }

    public Map<User, List<Transaction>> getMapTransaction() {
        return mapTransaction;
    }

    public void setMapTransaction(Map<User, List<Transaction>> mapTransaction) {
        this.mapTransaction = mapTransaction;
    }

    public void setData(List<Transaction> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if(mapTransaction.size() == 0) return "У вас еще не было транзакций!";

        return mapTransaction.entrySet()
                .stream()
                .map(e -> e.getKey() + " " + e.getValue())
                .collect(Collectors.joining());
    }

}
