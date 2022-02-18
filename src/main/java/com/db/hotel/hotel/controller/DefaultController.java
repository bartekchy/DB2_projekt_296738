package com.db.hotel.hotel.controller;

import com.db.hotel.hotel.model.Booking;
import com.db.hotel.hotel.model.Room;
import com.db.hotel.hotel.repository.BookingRepository;
import com.db.hotel.hotel.repository.RoomRepository;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @GetMapping("/panel")
    public String panel(Model model) {

        List<Date> dates = new ArrayList<Date>();
   //     long millis = System.currentTimeMillis();
   //     Date date = new Date(millis);
        Date date = Date.from(Instant.now().truncatedTo(ChronoUnit.DAYS).minusSeconds(60 * 60));

        for (int i = 0; i < 7; i++)
        {
            dates.add(date);
            date = new Date(date.getTime() + (1000 * 60 * 60 * 24));
        }

        List<Room> rooms = roomRepository.findAll();
        List<List<String>> data = new ArrayList<List<String>>();

        for (Room room : rooms )
        {
            List<String> items = new ArrayList<String>();
            items.add(room.getNumber().toString());
            for (Date date1 : dates )
            {
                Booking booking = bookingRepository.findByRoomDate(room.getId(), date1);
                System.out.println(date1);
                System.out.println(room);

                if( booking != null )
                {
                    items.add("X");
                }
                else
                {
                    items.add("");
                }
            }
            data.add(items);
        }

        model.addAttribute("dates", dates);
        model.addAttribute("data", data);

        return "booking/panel";
    }
}
