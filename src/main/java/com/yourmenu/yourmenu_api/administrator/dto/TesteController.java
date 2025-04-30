package com.yourmenu.yourmenu_api.administrator.dto;

import com.yourmenu.yourmenu_api.globalExceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TesteController {
    @GetMapping("test")
    public void teste(){
        throw  new UserNotFoundException(null);
    }
}
