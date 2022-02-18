package com.db.hotel.hotel.controller;

import com.db.hotel.hotel.model.Guest;
import com.db.hotel.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {

    @Autowired
    private GuestRepository guestRepository;

    @GetMapping("/guest/list")
    public String list(Model model) {
        model.addAttribute("guests", guestRepository.findAll());

        return "guest/list";
    }

    @GetMapping("/guest/create")
    public String create(Model model) {

        model.addAttribute("guest", new Guest());

        return "guest/create";
    }

    @PostMapping("/guest/create")
    public String createSubmit(@ModelAttribute Guest guest, Model model) {

        guestRepository.save(guest);

        return "redirect:/guest/list";
    }

    @GetMapping("/guest/delete/{guest}")
    public String delete(@PathVariable Guest guest, Model model) {

        model.addAttribute("guest", guest);
        return "guest/delete";
    }

    @PostMapping("/guest/delete/{guest}")
    public String deleteSubmit(@PathVariable Guest guest, Model model) {

        String index = guest.getId();
        if (index != null)
        {
            guestRepository.deleteById(index);
        }

        return "redirect:/guest/list";
    }

    @GetMapping("/guest/update/{guest}")
    public String update(@PathVariable Guest guest, Model model) {

        model.addAttribute("guest", guest);

        return "guest/update";
    }

    @PostMapping("/guest/update/{guest}")
    public String updateSubmit(@ModelAttribute Guest guest, Model model) {

        guestRepository.save(guest);

        return "redirect:/guest/list";
    }
}
