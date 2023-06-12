package shop.mtcoding.controller;

import shop.mtcoding.annotation.Controller;
import shop.mtcoding.annotation.RequestMapping;

@Controller
public class BoardController {

    @RequestMapping(uri = "/")
    public String main(){
        return "index";
    }

}
