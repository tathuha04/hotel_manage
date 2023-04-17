package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.OrderController;
import rikkei.academy.controller.RoomController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.*;
import rikkei.academy.service.room.RoomServiceIMPL;
import rikkei.academy.validate.Validate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookingRoomView {
    UserController userController = new UserController();
    private OrderController orderController = new OrderController();
    RoomController roomController = new RoomController();
//    List<Room> roomList = roomController.getListRoom();
    List<Order> orderList = orderController.getListOrder();
    public BookingRoomView() {
        User user = userController.getUserLogin();
        if (user!=null){
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName()== RoleName.USER){
                showListRoom();
                formBookingRoom();
            }else if (roles.get(0).getName() == RoleName.ADMIN){
                showReservationList();
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new ProfileView();
                }
            }
        }
    }

    public void showListRoom() {
        new RoomView().showFormRoomList();
    }

    public void formBookingRoom() {
        int idOrder = 0;
        if (orderList.size() == 0) {
            idOrder = 1;
        } else {
            idOrder = orderList.get(orderList.size() - 1).getId() + 1;
        }

//        RoomServiceIMPL roomCategory = new RoomServiceIMPL();
        List<Room> rooms = new ArrayList<>();
        int roomId;
        while (true) {
            System.out.println("Enter the room id you want to book");
            roomId = Config.scanner().nextInt();
            if (roomController.detailRoom(roomId) != null && !roomController.detailRoom(roomId).isStatus()) {
                roomController.detailRoom(roomId).setStatus(true);
                roomController.saveStatus();
                rooms.add(roomController.detailRoom(roomId));
                System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m of any key to continue");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    break;
                }
            } else {
                System.out.println("\u001B[38;2;255;51;51mId not found! Enter again\u001B[0m");
            }
        }
        String checkInRoom;
        while (true){
            System.out.println("Check in date(input in the format yyyy-MM-dd)");
            checkInRoom = Config.scanner().nextLine();
            if (!Validate.checkYear(checkInRoom)){
                System.out.println("\u001B[38;2;255;51;51mYear is not valid\u001B[0m");
            }else {
                System.out.println("\u001B[3m\u001B[32mYear is valid\u001B[0m");
                break;
            }
        }
        String checkOutRoom;
        while (true){
            System.out.println("Check in date(input in the format yyyy-MM-dd)");
            checkOutRoom = Config.scanner().nextLine();
            if (!Validate.checkYear(checkOutRoom)){
                System.out.println("\u001B[38;2;255;51;51mYear is not valid\u001B[0m");
            }else {
                System.out.println("\u001B[3m\u001B[32mYear is valid\u001B[0m");
                break;
            }
        }

        Order order = new Order(idOrder, rooms, checkInRoom, checkOutRoom);
        orderController.createOrder(order);
        System.out.println("Successful booking!");
        ///////////////////////
//        System.out.println(orderList);
        ///////////////////////
        System.out.println("Enter the \u001B[3m\u001B[32mback\u001B[0m to return Menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ProfileView();
        }
    }
    public void showReservationList(){
        System.out.println("――――――――――――――― RESERVATION LIST ―――――――――――――――");
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println("Id order : " + orderList.get(i).getId());
            System.out.println("User order : "+orderList.get(i).getUser());
            System.out.println("Room : "+orderList.get(i).getListRoom());
            System.out.println("Check in date : "+orderList.get(i).getCheckindate());
            System.out.println("Check out date : "+orderList.get(i).getCheckOutdate());
            System.out.println("\n―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― \n");
        }
    }
}