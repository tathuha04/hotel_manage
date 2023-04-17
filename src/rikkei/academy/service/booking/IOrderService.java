package rikkei.academy.service.booking;

import rikkei.academy.model.Order;
import rikkei.academy.model.Room;
import rikkei.academy.service.IGenericService;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;

public interface IOrderService extends IGenericService<Order> {
    public void orderByUser(List<Room> room, Order order);
}
