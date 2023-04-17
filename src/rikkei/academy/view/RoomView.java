package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.RoomController;
import rikkei.academy.model.Category;
import rikkei.academy.model.Room;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;
import rikkei.academy.validate.Validate;

import java.util.Collections;
import java.util.List;

public class RoomView {
    private ICategoryService categoryService = new CategoryServiceIMPL();

    private RoomController roomController = new RoomController();

    private List<Room> roomList = roomController.getListRoom();

    public void roomView() {
        System.out.println("\033[38;2;134;143;255m.――――――――――――――\033[0m ROOM MANAGE  \033[38;2;134;143;255m―――――――――――――.\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   1. Show hotel room list                               \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   2. Add new hotel room                                 \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   3. Edit hotel room information                        \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   4. Delete hotel room                                  \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   5. Hotel room details                                 \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m│\033[0m   6. Back                                               \033[38;2;134;143;255m│\033[0m");
        System.out.println("\033[38;2;134;143;255m・―――――――――――――――――――――――――――――――――――・\033[0m");
        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                showFormRoomList();
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    roomView();
                }
                break;
            case 2:
                formCreateRoom();
                break;
            case 3:
                formUpdateRoom();
                break;
            case 4:
                formDeleteRoom();
                break;
            case 5:
                formDetailRoom();
                break;
            case 6:
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String back = Config.scanner().nextLine();
                if (back.equalsIgnoreCase("back")) {
                    new ProfileView();
                }
                break;
        }
    }

    public void showFormRoomList() {
        Collections.sort(roomList, (o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));
        System.out.println("                 ROOM LIST  ");
        for (int i = 0; i < roomList.size(); i++) {
            System.out.println("- ID : " + roomList.get(i).getId());
            System.out.println("- Kind of room : " + roomList.get(i).getKindofroom());
            System.out.println("- Room number : " + roomList.get(i).getRoomnumber());
            System.out.println("- Price : " + roomList.get(i).getPrice());
            System.out.println("- Status : " + roomList.get(i).isStatus() + "\n");
            System.out.println("\u001B[38;5;208m――――――――――――――――――――――――――――――\u001B[0m");

        }
    }

    public void formCreateRoom() {
        while (true) {
            int id;
            if (roomList.size() == 0) {
                id = 1;
            } else {
                id = roomList.get(roomList.size() - 1).getId() + 1;
            }
            Category kindOfRoom;

            kindOfRoom = getCategoryFromList();
            String name;
            while (true) {
                System.out.println("Enter the room number");
                name = Config.scanner().nextLine();
                if (!Validate.checkPrice(name)) {
                    System.out.println("\u001B[38;2;255;51;51mName is not valid\u001B[0m");
                } else {
                    System.out.println("\u001B[3m\u001B[32mName is valid\u001B[0m");
                    break;
                }
            }
            int price;
            while (true) {
                System.out.println("Enter price");
                price = Integer.parseInt(Config.scanner().nextLine());
                if (!Validate.checkPrice(String.valueOf(price))) {
                    System.out.println("\u001B[38;2;255;51;51mPrice is not valid\u001B[0m");
                } else {
                    System.out.println("\u001B[3m\u001B[32mPrice is valid\u001B[0m");
                    break;
                }
            }
            boolean status = false;
            Room room = new Room(id, name, kindOfRoom, price, status);
            roomController.createRoom(room);
            System.out.println("\u001B[3m\u001B[32mCreate Success!\u001B[0m");
            System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                roomView();
                break;
            }
        }
    }

    private Category getCategoryFromList() {
        System.out.println(categoryService.findAll());
        System.out.println("Choose hotel room type");
        return categoryService.findById(Config.scanner().nextInt());
    }

    public void formUpdateRoom() {
        while (true) {
            System.out.println("Enter the id to update");
            int id = Config.scanner().nextInt();
            if (roomController.detailRoom(id) == null) {
                System.out.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                System.out.println("Enter the room number");
                String roomNumber = Config.scanner().nextLine();
                System.out.println("Enter the price");
                int price = Integer.parseInt(Config.scanner().nextLine());
                Category kindOfRoom = getCategoryFromList();
                Room room = new Room(id, kindOfRoom, roomNumber, price);
                roomController.updateRoom(room);
                new Config<Room>().writeToFile(Config.PATH_ROOM, roomList);
                System.out.println("\u001B[3m\u001B[32mUpdate success!\u001B[0m");
                System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    roomView();
                    break;
                }
            }
        }
    }

    private void formDeleteRoom() {
        while (true) {
            System.out.println("Enter the id to delete");
            int id = Config.scanner().nextInt();
            if (roomController.detailRoom(id) == null) {
                System.err.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                roomController.deleteRoom(id);
                System.out.println("\u001B[3m\u001B[32mDelete success!!\u001B[0m");
                System.out.println("Enter the any key to continue or Enter \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    roomView();
                    break;
                }
                break;
            }
        }
    }

    private void formDetailRoom() {
        while (true) {
            System.out.println("Enter the room id you want to see details");
            int id = Config.scanner().nextInt();
            if (roomController.detailRoom(id) == null) {
                System.err.println("\u001B[38;2;255;51;51mId not found! Please try again!\u001B[0m");
            } else {
                System.out.println("           LIST ROOM");
                for (int i = 0; i < roomList.size(); i++) {
                    if (roomList.get(i).getId() == id) {
                        System.out.println("- ID : " + roomList.get(i).getId());
                        System.out.println("- Kind of room : " + roomList.get(i).getKindofroom());
                        System.out.println("- Room number : " + roomList.get(i).getRoomnumber());
                        System.out.println("- Price : " + roomList.get(i).getPrice());
                        System.out.println("- Status : " + roomList.get(i).isStatus());
                    }
                }
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    roomView();
                }
            }
        }
    }

}
