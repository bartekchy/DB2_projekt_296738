package com.db.hotel.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("rooms")
public class Room {

    @Id
    private String id;
    private Integer number;
    private Integer capacity;
    private Float price;

    public Room() {
    }

    public Room(Integer number, Integer capacity, Float price) {
        this.number = number;
        this.capacity = capacity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}