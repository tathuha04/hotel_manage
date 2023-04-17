package rikkei.academy.controller;

import rikkei.academy.dto.response.ResponseMessage;
import rikkei.academy.model.Order;
import rikkei.academy.model.Room;
import rikkei.academy.service.booking.IOrderService;
import rikkei.academy.service.booking.OrderService;

import java.util.List;

public class OrderController {
    private IOrderService orderService = new OrderService();
    public List<Order> getListOrder(){
        return orderService.findAll();
    }
    public ResponseMessage createOrder(Order order){
        orderService.save(order);
        return new ResponseMessage("create_success");
    }

}
