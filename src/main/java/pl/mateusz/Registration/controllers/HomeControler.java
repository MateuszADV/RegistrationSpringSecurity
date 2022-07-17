package pl.mateusz.Registration.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.Registration.controllers.dto.UserRegistration;
import pl.mateusz.Registration.registration.RegistrationRequest;
import pl.mateusz.Registration.registration.RegistrationService;

@Controller
@AllArgsConstructor
public class HomeControler {

    private RegistrationService registrationService;

    @GetMapping("/")
    public String getIndex(){
        System.out.println("++++++++++++++++++++++++++++NAPIS TESTOWY++++++++++++++++++++++++++++++++++");
        return "home/index";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "home/about";
    }

    @GetMapping("/registration")
    public String getRegistration(ModelMap modelMap){
        modelMap.addAttribute("userForm", new UserRegistration());
        return "home/registration";
    }

    @PostMapping("/user/add")
    public String postCountry(@ModelAttribute("userForm") RegistrationRequest request, Model model){
        System.out.println("--------------------NAPIS TESTOWY--------------------------");
        System.out.println(request.toString());
//        RegistrationRequest request = new RegistrationRequest(
//                userRegistration.getFirstName(),
//                userRegistration.getLastName(),
//                userRegistration.getPassword(),
//                userRegistration.getEmail());
        registrationService.register(request);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/error")
    public String getError(){
        return "error";
    }

}
