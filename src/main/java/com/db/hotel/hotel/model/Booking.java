package com.db.hotel.hotel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document("booking")
public class Booking {

    public enum Status{
        NEW, BOOKING, CHECK_IN, CHECK_OUT;
    }

    @Id
    private String id;
    @DBRef
    private Room room;
    @DBRef
    private List<Guest> guests;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
    private Float price;

    public Booking() {
    }

    public Booking(Room room, List<Guest> guests, Status status, Date begin, Date end, Float price) {
        this.room = room;
        this.guests = guests;
        this.status = status;
        this.begin = begin;
        this.end = end;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", room=" + room +
                ", guests=" + guests +
                ", status=" + status +
                ", begin=" + begin +
                ", end=" + end +
                ", price=" + price +
                '}';
    }
}