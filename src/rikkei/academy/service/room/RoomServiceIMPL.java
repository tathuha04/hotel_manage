package rikkei.academy.service.room;

import rikkei.academy.config.Config;
import rikkei.academy.model.Category;
import rikkei.academy.model.Room;

import java.util.List;

public class RoomServiceIMPL implements IRoomService {
    List<Room> roomList = new Config<Room>().readFromFile(Config.PATH_ROOM);
    @Override
    public List<Room> findAll() {
        return roomList;
    }

    @Override
    public void save(Room room) {
        if (findById(room.getId())== null){
            roomList.add(room);
        }else {
            roomList.set(roomList.indexOf(findById(room.getId())),room );
        }
        new Config<Room>().writeToFile(Config.PATH_ROOM,roomList);
    }

    @Override
    public Room findById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getId()==id){
                return roomList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getId()==id){
                roomList.remove(i);
            }
        }
        new Config<Room>().writeToFile(Config.PATH_ROOM,roomList);
    }

    ///chi tiết từng phòng
    public Room showDetailById(int id){
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getId()==id){
                return roomList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setStatusRoom() {
        System.out.println("room list Service -->"+roomList);
        new Config<Room>().writeToFile(Config.PATH_ROOM,roomList);
    }
}
