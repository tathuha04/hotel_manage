package rikkei.academy.service.booking;

import rikkei.academy.config.Config;
import rikkei.academy.model.Order;
import rikkei.academy.model.Room;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.List;

public class OrderService implements IOrderService {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    List<Order> orderList = new Config<Order>().readFromFile(Config.PATH_ROOM_BOOKING);
    private IUserService userService = new UserServiceIMPL();


    @Override
    public List<Order> findAll() {
        return orderList;
    }

    @Override
    public void save(Order order) {
        User user = userService.getCurentUser();
        if (findById(order.getId())==null){
            order.setUser(user);
            orderList.add(order);
        }else {
            orderList.set(orderList.indexOf(findById(order.getId())),order);
        }
        new Config<Order>().writeToFile(Config.PATH_ROOM_BOOKING,orderList);
    }

    @Override
    public Order findById(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == id){
                return orderList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == id){
                orderList.remove(i);
            }
        }
    }

    @Override
    public void orderByUser(List<Room> room, Order order) {
        User user = userServiceIMPL.getCurentUser();
        order.setUser(user);
        orderList.add(order);
    }


}
