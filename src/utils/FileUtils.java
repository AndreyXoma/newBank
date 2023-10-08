package utils;

import entity.ListUsers;
import entity.User;
import entity.enums.UserRole;


import java.io.*;
import java.util.Scanner;


public class FileUtils {

    static String[] getPartsOfLine(String line) {
        return line.split(",");
    }

    private static UserRole defineUserRole(String[] partOfLine) {
        return switch (partOfLine[5]) {
            case "CUSTOMER" -> UserRole.CUSTOMER;
            case "ADMIN" -> UserRole.ADMIN;
            default -> throw new IllegalArgumentException("Данные не верны!");
        };
    }

    public static void readFile(ListUsers list, String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

            bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] partsOfLine = getPartsOfLine(line);

                UserRole userRole;
                userRole = defineUserRole(partsOfLine);

                User user = new User(partsOfLine[0], partsOfLine[1], partsOfLine[2], Integer.parseInt(partsOfLine[3]), Integer.parseInt(partsOfLine[4]), userRole, null);
                list.insertUser(user);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(ListUsers listUsers, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {

            for (int i = 0; i < listUsers.getSize(); i++) {

                bufferedWriter.write(listUsers.getUser(i) + "\n");
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeUser(User user, String fileName) {
        Scanner con = new Scanner(System.in);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {

            String[] arr = new String[]{user.getFullName(), ",",
                    user.getLogin(), ",",
                    user.getPassword(), ",",
                    String.valueOf(user.getAge()), ",",
                    String.valueOf(user.getBalance()), ",",
                    String.valueOf(user.getUserRole())};
            bufferedWriter.newLine();
            for (int i = 0; i < arr.length; i++) {
                bufferedWriter.write(arr[i]);
            }
            bufferedWriter.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void updateUser(User oldUser, User newUser, String fileName) {


        String oldLine = oldUser.getFullName() + "," + oldUser.getLogin() + "," + oldUser.getPassword() + "," + oldUser.getAge() + "," + oldUser.getBalance() + "," + oldUser.getUserRole();
        String newLine = newUser.getFullName() + "," + newUser.getLogin() + "," + newUser.getPassword() + "," + newUser.getAge() + "," + newUser.getBalance() + "," + newUser.getUserRole();

        try {
            File file = new File(fileName);
            File tempFile = new File("temp.txt");

            Scanner scanner = new Scanner(file);
            FileWriter writer = new FileWriter(tempFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.contains(oldLine)) {
                    line = line.replace(oldLine, newLine);
                }

                writer.write(line + "\n");
            }
            scanner.close();
            writer.close();

            if (file.delete()) {
                if (!tempFile.renameTo(file)) {
                    System.out.println("не удалось переименовать файл");
                }
            } else {
                System.out.println("Не удалось удалить исходный файл!");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
