package pl.mateusz.Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler {

    @GetMapping("/")
    public String getIndex(){
        System.out.println("++++++++++++++++++++++++++++NAPIS TESTOWY++++++++++++++++++++++++++++++++++");
        return "home/index";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "home/about";
    }
}
