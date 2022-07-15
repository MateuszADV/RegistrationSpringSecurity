package pl.mateusz.Registration.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContinentController {

    @GetMapping("continent")
    public String getIndex() {

        return "continent/index";
    }
}
