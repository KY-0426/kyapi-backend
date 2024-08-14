package com.kaiyu.kyapiinterface.controller;


import com.kaiyu.kyapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request){
        System.out.println(request.getHeader("kaiyu"));
        return "Get 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){
        return "POST 你的名字是"+name;
    }

    /**
     *  1 test
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/users")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request){
        System.out.println(request.getHeader("kaiyu"));
        return "POST 用户的名字是" + user.getUsername();
    }

}
