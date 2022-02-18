package com.db.hotel.hotel.controller;

import com.db.hotel.hotel.model.Room;
import com.db.hotel.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/room/list")
    public String list(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());

        return "room/list";
    }

    @GetMapping("/room/create")
    public String create(Model model) {

        model.addAttribute("room", new Room());

        return "room/create";
    }

    @PostMapping("/room/create")
    public String createSubmit(@ModelAttribute Room room, Model model) {

        roomRepository.save(room);

        return "redirect:/room/list";
    }

    @GetMapping("/room/delete/{room}")
    public String delete(@PathVariable Room room, Model model) {

        model.addAttribute("room", room);
        return "room/delete";
    }

    @PostMapping("/room/delete/{room}")
    public String deleteSubmit(@PathVariable Room room, Model model) {

        String index = room.getId();
        if (index != null)
        {
            roomRepository.deleteById(index);
        }

        return "redirect:/room/list";
    }

    @GetMapping("/room/update/{room}")
    public String update(@PathVariable Room room, Model model) {

        model.addAttribute("room", room);

        return "room/update";
    }

    @PostMapping("/room/update/{room}")
    public String updateSubmit(@ModelAttribute Room room, Model model) {

        roomRepository.save(room);

        return "redirect:/room/list";
    }
}

