package com.intech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by popikyardo on 23.07.15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/users")
    public String adminPage() {
        return "admin/userList";
    }
}
