package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();

    public ProfileView() {
        User user = userController.getUserLogin();
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName() == RoleName.ADMIN) {
                System.out.println("ADMIN");
                System.out.println("\033[38;2;223;107;149m.――――――――――――――\033[0m MY PROFILE \033[38;2;223;107;149m――――――――――――――.\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   1. Change password                                    \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   2. Change profile                                     \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   3. Go to menu Category                                \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   4. Go to menu Room                                    \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   5. Hotel reservation management                       \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   6. Back                                               \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m・―――――――――――――――――――――――――――――――――――・\033[0m");
                int chooseMenu = Config.scanner().nextInt();
                switch (chooseMenu) {
                    case 1:
                        new ChangeProfileView().changePassword();
                        break;
                    case 2:
                        new ChangeProfileView().changeProfile();
                        break;
                    case 3:
                        new CategoryView();
                        break;
                    case 4:
                        new RoomView().roomView();
                        break;
                    case 5:
                        new BookingRoomView();
                        break;
                    case 6:
                        new Navbar();
                        break;
                }
            } else if (roles.get(0).getName() == RoleName.USER) {
                System.out.println("USER");
                System.out.println("\033[38;2;223;107;149m.――――――――――――――\033[0m MY PROFILE \033[38;2;223;107;149m――――――――――――――.\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   1. Change password                                    \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   2. Change profile                                     \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   3. Book hotel room                                    \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m│\033[0m   4. Back                                               \033[38;2;223;107;149m│\033[0m");
                System.out.println("\033[38;2;223;107;149m・―――――――――――――――――――――――――――――――――――・\033[0m");
                int chooseMenu = Config.scanner().nextInt();
                switch (chooseMenu) {
                    case 1:
                        new ChangeProfileView().changePassword();
                        break;
                    case 2:
                        new ChangeProfileView().changeProfile();
                        break;
                    case 3:
                        new BookingRoomView();
                        break;
                    case 4:
                        new Navbar();
                        break;
                }
            }
        }
    }
}
