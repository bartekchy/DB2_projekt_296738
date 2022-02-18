package com.db.hotel.hotel.service;

import com.db.hotel.hotel.model.Booking;
import com.db.hotel.hotel.model.Room;
import com.db.hotel.hotel.repository.BookingRepository;
import com.db.hotel.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getFreeRooms (Booking booking){

        Date begin = booking.getBegin();
        Date end = booking.getEnd();

        List<Booking> bookingInRange = bookingRepository.findByRange(begin, end);

        List<String> conflicts = new ArrayList<String>();

        for (Booking conflict : bookingInRange) {
            conflicts.add(conflict.getRoom().getId());
        }

        List<Room> rooms =  roomRepository.findByConflicts(conflicts);

        if (booking.getRoom() != null){
            rooms.add(0, booking.getRoom());
        }

        return rooms;
    }
}
