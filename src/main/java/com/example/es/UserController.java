package com.example.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    StandardService standardService;


    @RequestMapping("/demo")
    public String add(){
        User dz = new User("dz1", 32, new Date(), "333333333333333333333");
        standardService.addUser(dz);
        return "1";
    }
    @RequestMapping("/name")
    public List find(){
       return  standardService.queryByUserName("dz");
    }
    @RequestMapping("/name1")
    public Iterable<User> search(){
        return  standardService.search();
    }
}
