package com.db.hotel.hotel.controller;

import com.db.hotel.hotel.model.User;
import com.db.hotel.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {

        return "user/login";
    }

    @GetMapping("/register")
    public String register() {

        return "register";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("user", new User());

        return "user/create";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute User user, Model model) {
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);

        return "redirect:/list";
    }

    @GetMapping("/delete/{user}")
    public String delete(@PathVariable User user, Model model) {


        model.addAttribute("user", user);
        return "user/delete";
    }

    @PostMapping("/delete/{user}")
    public String deleteSubmit(@PathVariable User user, Model model) {

        String index = user.getId();
        if (index != null)
        {
            userRepository.deleteById(index);
        }

        return "redirect:/list";
    }

    @GetMapping("/update/{user}")
    public String update(@PathVariable User user, Model model) {

        user.setPassword("");
        model.addAttribute("user", user);

        return "user/update";
    }

    @PostMapping("/update/{user}")
    public String updateSubmit(@ModelAttribute User user, Model model) {

        if (user.getPassword() == "")
        {
            Optional<User> userFromDB =  userRepository.findById(user.getId());
            user.setPassword(passwordEncoder.encode(userFromDB.get().getPassword()));
        }
        else
        {
            String pass = passwordEncoder.encode(user.getPassword());
            user.setPassword(pass);
        }

        userRepository.save(user);

        return "redirect:/list";
    }
/*
    @PostMapping("/register")
    public ModelAndView registerPost(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password2,
            @RequestParam String email) throws Exception {

        if (!password.equals(password2)) {
            throw new Exception();
        }
        if (userRepository.existsByUsername(username)) {
            throw new Exception();
        }
        User user = new User(username, passwordEncoder.encode(password), email);
        userRepository.save(user);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/username")
    @ResponseBody
    public String username(Principal principal) {

        return principal.getName();
    }
    */
}

