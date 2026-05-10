
package com.Forniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LivingRoomController {
    
    
    @GetMapping({"/sala" })
    public String indexLivingRoom(){
        
        return "LivingRoom/index";
    }
    
    @GetMapping({"/producto"})
    public String detailsLivingRoom(){
        
        return ("LivingRoom/Details");
    }
}
