package com.order.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class OrderController {

    @RequestMapping("/test")
    public String index(){
        return "hello word";
    }


}
