package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config<T> {
    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    public static final String PATH_CATEGORY = "F:\\RIKKEI_ACADEMY\\MODULE_3\\MD3-CRUD-Category-Binary-master\\src\\rikkei\\academy\\database\\category.txt";
    public static final String PATH_USER = "F:\\RIKKEI_ACADEMY\\MODULE_3\\MD3-CRUD-Category-Binary-master\\src\\rikkei\\academy\\database\\user.txt";
    public static final String PATH_USER_LOGIN = "F:\\RIKKEI_ACADEMY\\MODULE_3\\MD3-CRUD-Category-Binary-master\\src\\rikkei\\academy\\database\\userLogin.txt";
    public static final String PATH_ROOM_BOOKING = "F:\\RIKKEI_ACADEMY\\MODULE_3\\MD3-CRUD-Category-Binary-master\\src\\rikkei\\academy\\database\\roomBooking.txt";
    public static final String PATH_ROOM = "F:\\RIKKEI_ACADEMY\\MODULE_3\\MD3-CRUD-Category-Binary-master\\src\\rikkei\\academy\\database\\room.txt";
    //Phương thức đọc file
    public List<T> readFromFile(String pathFile) {
        List<T> tList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            tList = (List<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (FileNotFoundException f) {
            System.err.println("File not found!");
        } catch (IOException i) {
            System.err.println("IOE exception!");
        } catch (ClassNotFoundException c) {
            System.err.println("Class Not Found");
        }
        return tList;
    }

    //Phương thức ghi file
    public void writeToFile(String pathFile, List<T> tList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(tList);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (FileNotFoundException f) {
            System.err.println("File Not Found!");
        } catch (IOException i) {
            System.err.println("IOE Exception!");
        }
    }
}
