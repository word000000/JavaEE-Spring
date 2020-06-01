package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author:GQM
 * @Date:created in 1:30 2020/6/1
 * @Description:
 * @Modifyed_By:
 */
@Controller
@RequestMapping("/")
public class MainController {
    @RequestMapping("login")
    public String index() {
        return "index";
    }
    @RequestMapping("main")
    public String a() {
        return "login";
    }
}