package entity;

import java.util.*;

public class ListUsers {

    private List<User> dataUser;

    private int size = 0;

    public ListUsers() {
        dataUser = new ArrayList<>();
    }
    public int getSize() {
        return dataUser.size();
    }

    public User getUser(int index) {
        if(index < 0) {
            return null;
        }
        return dataUser.get(index);
    }


    public int searchUser(String login) {
        for(User user : dataUser) {
            if(user.getLogin().equals(login)) {
                return dataUser.indexOf(user);
            }
        }
        return -1;
    }

    public void insertUser(User user) {
        if(user != null) {
            dataUser.add(user);
        } else {
            System.out.println("Error!!!");
        }
    }

    public void insertAdmin(Admin admin) {
        if(admin != null) {
            dataUser.add(admin);
        } else {
            System.out.println("Error!!!");
        }
    }

    public  void updateUsers(User oldElement, User newElement) {
        for (int i = 0; i < dataUser.size(); i++) {
            if(dataUser.get(i).equals(oldElement)) {
                dataUser.set(i, newElement);
            }
        }
    }


    public List<User> getUserList() {
        return dataUser;
    }

    public void setUserList(List<User> dataUser) {
        this.dataUser = dataUser;
    }


    @Override
    public String toString() {
        if(dataUser.size() == 0) return "Пользователей нету!";

        final StringBuilder stringBuilder = new StringBuilder();
        for (User element : dataUser) {
            stringBuilder.append(element.toString());
        }
        return stringBuilder.toString();
    }
}
