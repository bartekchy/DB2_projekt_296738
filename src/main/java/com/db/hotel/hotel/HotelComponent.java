package com.db.hotel.hotel;

import com.db.hotel.hotel.model.Booking;
import com.db.hotel.hotel.model.Guest;
import com.db.hotel.hotel.model.Room;
import com.db.hotel.hotel.model.User;
import com.db.hotel.hotel.repository.BookingRepository;
import com.db.hotel.hotel.repository.GuestRepository;
import com.db.hotel.hotel.repository.RoomRepository;
import com.db.hotel.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class HotelComponent {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {

        if (userRepository.count() < 1) {
            String username = "admin";
            String password = passwordEncoder.encode("admin");
            User.Role role = User.Role.MANAGER;
            User user = new User(username, password, role);
            userRepository.save(user);
        }

        if (roomRepository.count() < 1) {
            Integer number = 1;
            Integer capacity = 1;
            Float   price = 1.1f;
            Room room = new Room(number, capacity, price);
            roomRepository.save(room);
        }

        if (guestRepository.count() < 1) {
            String forename = "guest";
            String surname = "guest";
            String pesel = "77399417296";
            Guest guest = new Guest(forename, surname, pesel);
            guestRepository.save(guest);
        }

        if (bookingRepository.count() < 1) {
            Room room = roomRepository.findAll().get(0);
            List<Guest> guests = guestRepository.findAll();
            Booking.Status status = Booking.Status.BOOKING;
            Date begin = new Date(2014, 02, 11);
            Date end = new Date(2014, 03, 12);
            Float price = 100.0f;

            Booking booking = new Booking(room, guests, status,begin,end,price);
            bookingRepository.save(booking);
        }
    }
}