package com.stackroute.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stackroute.dao.UserDao;
import com.stackroute.dao.UserDaoImpl;
import com.stackroute.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {


    @Autowired
    UserDao userDao = new UserDaoImpl();

    private User user = new User();

    @GetMapping
    public String indexPage(ModelMap model) {
        model.addAttribute("tracks", userDao.getAllTracks());
        return "index";
    }

    @GetMapping("login")
    public String addTrack(@RequestParam("userName") String name, @RequestParam("userPassword") String password, ModelMap model) {
        if (name.isEmpty() || password.isEmpty()) {
            return "index";
        } else {
            user.setUsername(name);
            user.setPassword(password);
            boolean status = userDao.saveTrack(user);
            model.addAttribute("username",user.getUsername());
            if (status) {
                model.addAttribute("users", userDao.getAllTracks());
                return "login";
            } else {
                return "index";
            }

        }

    }
}


