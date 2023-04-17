package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.validate.Validate;

public class ChangeProfileView {
    private UserController userController = new UserController();

    public void changePassword() {
        User user = userController.getUserLogin();
        String currentPassword;
        while (true) {
            System.out.println("Enter your current password");
            currentPassword = Config.scanner().nextLine();
            if (!user.getPassWord().equals(currentPassword)) {
                System.err.println("\u001B[38;2;255;51;51mInvalid password!\u001B[0m");
            } else {
                break;
            }
        }

        System.out.println("Enter the new password");
        String newPassword = Config.scanner().nextLine();

        System.out.println("Enter the new password again");
        String confirmPassword = Config.scanner().nextLine();

        while (true) {
            if (!newPassword.equals(confirmPassword)) {
                System.err.println("\u001B[38;2;255;51;51mThe new password and confirm password do not match!\u001B[0m");
                System.out.println("Enter the new password again");
                confirmPassword = Config.scanner().nextLine();
            } else {
                System.out.println("\u001B[3m\u001B[32mChange password successfully\u001B[0m");

                break;
            }
        }

        user.setPassWord(newPassword);
        userController.UpdateUser(user);
        System.out.println(user.getPassWord());
    }

    public void changeProfile() {
        User editProfile = userController.getUserLogin();
        while (true) {
            System.out.println("Enter the name");
            String name = Config.scanner().nextLine();
            if (!Validate.checkName(name)) {
                System.err.println("\u001B[38;2;255;51;51mIncorrect name format\u001B[0m");
            } else {
                editProfile.setName(name);
                System.out.println("\u001B[3m\u001B[32mvalid name!\u001B[0m");
                break;
            }
        }

        while (true) {
            System.out.println("Enter the email");
            String email = Config.scanner().nextLine();
            if (!Validate.checkEmail(email)) {
                System.err.println("\u001B[38;2;255;51;51mIncorrect email format\u001B[0m");
            } else {
                editProfile.setEmail(email);
                System.out.println("\u001B[3m\u001B[32mvalid email!\u001B[0m");
                break;
            }
        }
        System.out.println("\u001B[3m\u001B[32mSuccessfully updated!\u001B[0m");
        userController.UpdateUser(editProfile);
        new ProfileView();
        System.out.println(userController.getListUser());
    }

}
