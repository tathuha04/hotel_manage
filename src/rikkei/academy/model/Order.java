package rikkei.academy.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private User user;
    private List<Room> listRoom;
    private LocalDate checkindate;
    private LocalDate checkOutdate;

    public Order(int idRoom, String checkInRoom, String checkOutRoom) {
        this.id = idRoom;
        checkindate = LocalDate.parse(checkInRoom);
        checkOutdate = LocalDate.parse(checkOutRoom);
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + listRoom +
                ", checkindate=" + checkindate +
                ", checkOutdate=" + checkOutdate +
                '}'+"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Room> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<Room> room) {
        this.listRoom = room;
    }

    public LocalDate getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        checkindate = checkindate;
    }

    public LocalDate getCheckOutdate() {
        return checkOutdate;
    }

    public void setCheckOutdate(String checkOutdate) {
        checkOutdate = checkOutdate;
    }

    public Order(int id, List<Room> room, String checkindate, String checkOutdate) {
        this.id = id;
        this.user = user;
        this.listRoom = room;
        checkindate = checkindate;
        checkOutdate = checkOutdate;
    }
}
