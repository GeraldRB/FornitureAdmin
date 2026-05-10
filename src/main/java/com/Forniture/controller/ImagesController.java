
package com.Forniture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImagesController {
    
    
    @GetMapping({"/Create" })
    public String createImages(){
        
        return "Images/create";
    }

}
