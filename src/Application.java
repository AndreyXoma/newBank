import entity.*;
import entity.enums.TypeTransaction;
import entity.enums.UserRole;
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.ls.LSOutput;
import service.Authorization;
import utils.AdminMenu;
import utils.FileUtils;
import utils.Menu;
import utils.UserMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Application {

    private static ListTransaction listTransaction  = new ListTransaction(new ArrayList<>());
    private static ListUsers users = new ListUsers();
    static final Authorization authorization = new Authorization(users);
    static final Scanner console = new Scanner(System.in);

    public static void run() {
        Menu menu;

        FileUtils.readFile(users, "src/users.csv");
        System.out.println("Ты в приложении банка, тебе надо авторизироваться!");


        Pair<User, Menu> userMenuPair = tryToAuthoriseUser();
        menu = userMenuPair.getSecondValue();
        User user = userMenuPair.getFirstValue();
        menu.printMenu();

        switch (user.getUserRole()) {
            case ADMIN -> {
                int number;
                Admin admin = new Admin(user);
                do {
                    System.out.println("Введите номер задачи:");
                    number = console.nextInt();

                    switch (number) {
                        case 1 -> System.out.println(users);
                        case 2 -> {
                            createUser();
                        }
                        case 3 -> {
                            System.out.println(admin);
                        }
                        case 4 -> {
                            updateAdmin(admin, user);
                            System.out.println("Данные изменены!");
                            return;
                        }
                        case 5 -> {
                            System.out.println("Досвидание " + admin.getFullName());
                            return;
                        }
                    }
                } while (number != 5);
            }
            case CUSTOMER -> {
                int number;
                Customer customer = new Customer(user);
                do {
                    System.out.println("Введите номер задачи:");
                    number = console.nextInt();
                    switch (number) {
                        case 1 -> {
                            System.out.println("Ваш баланс на данный момент времени составляет " + user.getBalance() + "р.");
                        }
                        case 2 -> {
                            System.out.println("Введите логин нужного пользователя:");
                            console.nextLine();
                            String login = console.nextLine();
                            User transUser = users.getUser(users.searchUser(login));

                            if(transUser == null) {
                                System.out.println("Пользователь не найден");
                            } else {
                                System.out.println("Введите сумму для перевода:");
                                int money = console.nextInt();
                                int balance = customer.getBalance();
                                if(money > balance || money <= 0) {
                                    System.out.println("Некорректная сумма перевода");
                                } else {
                                    if(transUser.getUserRole().equals(UserRole.CUSTOMER)) {

                                        Transaction transaction = new Transaction(customer, transUser, TypeTransaction.TRANSFER, money, new Date());
                                        listTransaction.createTrans(customer, transaction);

                                        System.out.println("Теперь ваш баланс составляет: " + (customer.getBalance() - money) + "р");

                                        updateBalanceMinus(customer, user, money);

                                        Customer oldUser = new Customer(transUser);
                                        updateBalancePlus(oldUser, transUser,  money);
                                    } else {
                                        System.out.println("Вы не можете перевести деньги данному пользователю!");
                                    }


                                }

                            }
                        }
                        case 3 -> {
                            System.out.println(user);
                        }
                        case 4 -> {
                            updateCustomer(customer, user);
                            System.out.println("Данные изменены!");
                            return;
                        }
                        case 5 -> {
                            System.out.println("Ваша история переводов:");
                            listTransaction.printTrans(customer);

                        }
                        case 6 -> {
                            System.out.println("Досвидание " + user.getFullName());
                            return;
                        }

                    }
                } while (number != 6);
            }


        }
    }

    private static Pair<User, Menu> tryToAuthoriseUser() {
        Menu menu;
        System.out.println("Введите ваш логин:");
        String login = console.nextLine();
        System.out.println("Введите ваш пароль:");
        String password = console.nextLine();

        User authUser = authorization.auth(login, password);

        if (authUser == null) {
            System.out.println("Error: Неверный логин и пароль!");
        } else {

            System.out.println("Здравствуйте " + authUser.getFullName());

        }
        UserRole userRole = authorization.authorize(authUser);

        menu = switch (userRole) {
            case ADMIN -> new AdminMenu();
            case CUSTOMER -> new UserMenu();
        };
        return new Pair<>(authUser, menu);


    }

    private static void createUser() {

        UserRole role = null;
        System.out.print("Какого пользователя вы хотите создать: \n" +
                "1. " + UserRole.CUSTOMER + "\n" +
                "2. " + UserRole.ADMIN + "\n");

        int type = console.nextInt();

        if (type == 1) {
            role = UserRole.CUSTOMER;
        } else if (type == 2) {
            role = UserRole.ADMIN;
        } else {
            System.out.println("error");
        }

        if (role != null) {
            System.out.println("Введите данные нового пользователя!");

            System.out.println("Введите ФИО:");
            console.nextLine();
            String fullName = console.nextLine();
            System.out.println("Введите логин:");
            String login = console.nextLine();
            System.out.println("Введите пароль:");
            String password = console.nextLine();
            System.out.println("Введите возраст:");
            int age = console.nextInt();
            System.out.println("Введите баланс:");
            int balance = console.nextInt();

            if (role.equals(UserRole.CUSTOMER)) {
                Customer customer = new Customer(fullName, login, password, age, balance, UserRole.CUSTOMER, null);
                users.insertUser(customer);
                FileUtils.writeUser(customer, "src/users.csv");

            } else {
                Admin admin = new Admin(fullName, login, password, age, balance, UserRole.ADMIN, null, users);
                users.insertAdmin(admin);
                FileUtils.writeUser(admin, "src/users.csv");
            }

        } else {
            System.out.println("error");
        }

    }

    private static void updateCustomer(Customer customer, User user) {
        System.out.println("Введите новые данные:");
        System.out.println("Введите ФИО:");
        console.nextLine();
        String fullName = console.nextLine();
        System.out.println("Введите логин:");
        String login = console.nextLine();
        System.out.println("Введите пароль:");
        String password = console.nextLine();
        System.out.println("Введите возраст:");
        int age = console.nextInt();

        customer.updateCustomer(fullName, login, password, age);
        users.updateUsers(user, customer);
        FileUtils.updateUser(user, customer ,"src/users.csv");

    }

    private static void updateBalanceMinus(Customer customer, User user, int money) {
        int balance = user.getBalance() - money;
        customer.updateBalance(balance);
        users.updateUsers(user, customer);
        FileUtils.updateUser(user, customer, "src/users.csv");
    }

    private static void updateBalancePlus(Customer customer,User user, int money) {

        int balance = user.getBalance() + money;
        customer.updateBalance(balance);
        users.updateUsers(user, customer);
        FileUtils.updateUser(user, customer, "src/users.csv");
    }

    private static void updateAdmin(Admin admin, User user) {
        System.out.println("Введите новые данные:");
        System.out.println("Введите ФИО:");
        console.nextLine();
        String fullName = console.nextLine();
        System.out.println("Введите логин:");
        String login = console.nextLine();
        System.out.println("Введите пароль:");
        String password = console.nextLine();
        System.out.println("Введите возраст:");
        int age = console.nextInt();

        admin.updateAdmin(fullName, login, password, age);
        users.updateUsers(user, admin);
        FileUtils.updateUser(user, admin, "src/users.csv");

    }

}
