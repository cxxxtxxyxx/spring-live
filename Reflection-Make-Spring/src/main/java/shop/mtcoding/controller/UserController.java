package shop.mtcoding.controller;

import shop.mtcoding.annotation.Controller;
import shop.mtcoding.annotation.RequestMapping;
import shop.mtcoding.annotation.ResponseBody;
import shop.mtcoding.model.User;

@Controller
public class UserController {

    @RequestMapping(uri = "/login")
    @ResponseBody
    public User login(){
        System.out.println("login() 호출됨");
        User user = new User(1, "ssar", "1234", "ssar@nate.com");
        return user;
    }

    @RequestMapping(uri = "/joinForm")
    public String joinForm(){
        System.out.println("join() 호출됨");
        return "user/joinForm";
    }
}
