package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.User;
import rikkei.academy.validate.Validate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {

    UserController userController = new UserController();
    List<User> userList = userController.getListUser();

    public void register() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        String name;
        while (true) {
            System.out.println("Enter the name");
            name = Config.scanner().nextLine();
            if (!Validate.checkName(name)) {
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter\u001B[0m");
            } else {
                System.out.println("\u001B[3m\u001B[32mvalid name!\u001B[0m");
                break;
            }
        }

        String username;
        while (true) {
            System.out.println("Enter the username");
            username = Config.scanner().nextLine();
            if (!Validate.checkUserName(username)) {
                username = Config.scanner().nextLine();
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter\u001B[0m");
            } else {
                System.out.println("\u001B[3m\u001B[32mvalid username\u001B[0m");
                break;
            }
        }

        String email;
        while (true) {
            System.out.println("Enter the email");
            email = Config.scanner().nextLine();
            if (!Validate.checkEmail(email)) {
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter!\u001B[0m");
            } else {
                System.out.println("\u001B[3m\u001B[32mEmail in correct format\u001B[0m");
                break;
            }
        }
        String password;
        while (true) {
            System.out.println("Enter the password");
            password = Config.scanner().nextLine();
            if (!Validate.checkPassword(password)) {
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter!\u001B[0m");
            } else {
                System.out.println("\u001B[3m\u001B[32mThe password is in the correct format\u001B[0m");
                break;
            }
        }

        System.out.println("Enter the role");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);

        SignUpDTO sign = new SignUpDTO(id, name, username, email, password, strRole);

        while (true) {
            ResponseMessage message = userController.register(sign);
            if (message.getMessage().equals("user_existed")) {
                System.out.println("\u001B[38;2;255;51;51mUsername is existed! Please try again!\u001B[0m");
                username = Config.scanner().nextLine();
                sign.setUserName(username);
            }
            if (message.getMessage().equals("email_existed")) {
                System.out.println("\u001B[38;2;255;51;51mEmail is existed! Please try again!\u001B[0m");
                email = Config.scanner().nextLine();
                sign.setEmail(email);
            }
            if (message.getMessage().equals("create_success")) {
                System.out.println("\u001B[3m\u001B[32mRegister success\u001B[0m");
                formLogin();
                break;
            }
        }

    }

    public void formLogin() {
        System.out.println("       LOG IN");
        String username;
        while (true) {
            System.out.println("Enter the username");
            username = Config.scanner().nextLine();
            if (!Validate.checkLoginUsername(username)) {
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter!\u001B[0m");
            } else {
                break;
            }
        }

        String password;
        while (true) {
            System.out.println("Enter the password");
            password = Config.scanner().nextLine();
            if (!Validate.checkLogin(password)) {
                System.out.println("\u001B[38;2;255;51;51mPlease re-enter!\u001B[0m");
            } else {
                break;
            }
        }
        SignInDTO signInDTO = new SignInDTO(username, password);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if (responseMessage.getMessage().equals("Login_false")) {
                System.out.println("\u001B[38;2;255;51;51mLogin failed! Please check your account!\u001B[0m");
                System.out.println("Enter the username ");
                username = Config.scanner().nextLine();
                System.out.println("Enter the password");
                password = Config.scanner().nextLine();
                signInDTO.setPassword(password);
                signInDTO.setUsername(username);

            } else {
                System.out.println("\u001B[3m\u001B[32mLogin success\u001B[0m");
                new Navbar();
                break;
            }
        }
    }

    public void showListUser() {
        System.out.println(userController.getListUser());
        System.out.println("Enter '\u001B[3m\u001B[32mback' to return navbar");
        String back = Config.scanner().nextLine();
        if (back.equalsIgnoreCase("back")) {
            new Navbar();
        }
    }

}
