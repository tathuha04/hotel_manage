package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;


public class Navbar {
    UserController userController = new UserController();

    public Navbar() {
        User user = userController.getUserLogin();
        if (user != null) {
            System.out.println("welcome " + user.getName() + "\n" +
                    "1. Go to profile\n" +
                    "2. Log out");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
                    new ProfileView();
                    break;
                case 2:
                    userController.userLogout();
                    new Navbar();
                    break;
                default:
                    System.out.println("\u001B[38;2;255;51;51mYour selection does not match, please re-enter!\u001B[0m");
                    new Navbar();
                    break;
            }
        } else {
            System.out.println("1. Register");
            System.out.println("2. Login");
            int chooseMenu = Config.scanner().nextInt();
            switch (chooseMenu) {
                case 1:
                    new UserView().register();
                    break;
                case 2:
                    new UserView().formLogin();
                    break;
                case 3:
                    break;
                case 5:
                    new UserView().showListUser();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new Navbar();
    }
}