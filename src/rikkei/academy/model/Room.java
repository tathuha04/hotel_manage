package rikkei.academy.model;

import java.io.Serializable;

public class Room implements Serializable {
    private int id;
    private String roomnumber;
    private Category Kindofroom;
    private int price;

    private boolean status;

    public Room() {
    }
    public Room(int id, Category kindofroom, String roomnumber, int price) {
        this.id = id;
        this.Kindofroom = kindofroom;
        this.roomnumber = roomnumber;
        this.price = price;
    }

    public Room(int id, String roomnumber, Category kindofroom, int price, boolean status) {
        this.id = id;
        this.roomnumber = roomnumber;
        Kindofroom = kindofroom;
        this.price = price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    public Category getKindofroom() {
        return Kindofroom;
    }

    public void setKindofroom(Category kindofroom) {
        Kindofroom = kindofroom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomnumber=" + roomnumber +
                ", Kindofroom=" + Kindofroom +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

}
