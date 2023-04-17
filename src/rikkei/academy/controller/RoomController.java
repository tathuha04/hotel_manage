package rikkei.academy.controller;

import rikkei.academy.model.Room;
import rikkei.academy.service.room.IRoomService;
import rikkei.academy.service.room.RoomServiceIMPL;

import java.util.List;

public class RoomController {
    private static IRoomService roomService = new RoomServiceIMPL();

    public List<Room> getListRoom(){
        return roomService.findAll();
    }
    public void createRoom(Room room){
        roomService.save(room);
    }
    public void updateRoom(Room room){
        roomService.save(room);
    }
    public Room detailRoom(int id){
        return roomService.findById(id);
    }
    public void deleteRoom(int id){
        roomService.deleteById(id);
    }
    public void saveStatus(){
        roomService.setStatusRoom();
    }
}
