package com.db.hotel.hotel.controller;

import com.db.hotel.hotel.model.Booking;
import com.db.hotel.hotel.model.Guest;
import com.db.hotel.hotel.model.Room;
import com.db.hotel.hotel.repository.BookingRepository;
import com.db.hotel.hotel.repository.GuestRepository;
import com.db.hotel.hotel.repository.RoomRepository;
import com.db.hotel.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/create/{guest}")
    public String create(@PathVariable Guest guest) {
        List<Guest> guests = Arrays.asList(guest);
        Booking booking = new Booking();
        booking.setGuests(guests);
        booking.setStatus(Booking.Status.NEW);

        bookingRepository.save(booking);
        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/date/{booking}")
    public String Date(@PathVariable Booking booking, Model model) {

        model.addAttribute("booking", booking);

        return "booking/date";
    }

    @PostMapping("/booking/date/{booking}")
    public String dateSubmit(@ModelAttribute Booking booking, Model model) {
        booking.setStatus(Booking.Status.BOOKING);
        booking.setRoom(null);
        booking.setPrice(null);
        bookingRepository.save(booking);
        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/room/{booking}")
    public String room(@PathVariable Booking booking, Model model) {

        List<Room> rooms = bookingService.getFreeRooms(booking);

        model.addAttribute("booking", booking);
        model.addAttribute("rooms", rooms);

        return "booking/room";
    }

    @PostMapping("/booking/room/{booking}")
    public String roomSubmit(@PathVariable Booking booking, @RequestParam Room room, Model model) {
        booking.setRoom(room);
        bookingRepository.save(booking);

        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/read/{booking}")
    public String read(@PathVariable Booking booking, Model model) {

        model.addAttribute("booking", booking);

        return "booking/read";
    }

    @GetMapping("/booking/list")
    public String list(Model model) {

        model.addAttribute("booking", bookingRepository.findAll());

        return "booking/list";
    }

    @GetMapping("/booking/guest/{booking}")
    public String guest(@PathVariable Booking booking, Model model) {

        model.addAttribute("guests", guestRepository.findAll());

        return "booking/guest";
    }

    @PostMapping("/booking/guest/{booking}")
    public String guestSubmit(@PathVariable Booking booking, @RequestParam Guest guest, Model model) {

        List<Guest> guests = booking.getGuests();
        guests.add(guest);
        booking.setGuests(guests);
        bookingRepository.save(booking);

        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/delguest/{booking}/{guest}")
    public String guest(@PathVariable Booking booking, @PathVariable Guest guest, Model model) {

        List<Guest> guests = booking.getGuests();
        // https://stackoverflow.com/questions/8520808/how-to-remove-specific-object-from-arraylist-in-java
        for(int j = 0; j < guests.size(); j++) {
            if (guests.get(j).getId().equals(guest.getId())) {
                guests.remove(j);
                break;
            }
        }

        booking.setGuests(guests);
        bookingRepository.save(booking);

        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/status/{booking}")
    public String status(@PathVariable Booking booking, Model model) {

        model.addAttribute("booking", booking);

        return "booking/status";
    }

    @PostMapping("/booking/status/{booking}")
    public String statusSubmit(@ModelAttribute Booking booking, Model model) {

        bookingRepository.save(booking);

        return "redirect:/booking/read/" + booking.getId();
    }

    @GetMapping("/booking/delete/{booking}")
    public String delete(@PathVariable Booking booking) {

        bookingRepository.delete(booking);

        return "redirect:/booking/list/";
    }
}