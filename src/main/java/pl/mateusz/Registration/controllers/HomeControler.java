package pl.mateusz.Registration.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mateusz.Registration.controllers.dto.UserRegistration;
import pl.mateusz.Registration.registration.EmailValidator;
import pl.mateusz.Registration.registration.RegistrationRequest;
import pl.mateusz.Registration.registration.RegistrationService;
import pl.mateusz.Registration.security.config.UserCheckLoged;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.zip.DataFormatException;

@Controller
@AllArgsConstructor
public class HomeControler {

    private RegistrationService registrationService;
    private final EmailValidator emailValidator;
    private UserCheckLoged userCheckLoged;




    @GetMapping("/")
    public String getIndex(ModelMap modelMap,
                           HttpServletRequest request,
                           HttpServletResponse response){
        System.out.println("++++++++++++++++++++++++++++NAPIS TESTOWY++++++++++++++++++++++++++++++++++");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(userCheckLoged.UserCheckLoged().getName());
//        System.out.println(userCheckLoged.UserCheckLoged().getAuthorities());
//        System.out.println(userCheckLoged.UserCheckLoged().getCredentials());
//        System.out.println(userCheckLoged.UserCheckLoged().getPrincipal().getClass().getCanonicalName());

        modelMap.addAttribute("test", "NAPIS TESTOWY");

        System.out.println("---------------------TEST START------------------------");
        System.out.println(request.toString());
        System.out.println(response.getLocale().toString());
        System.out.println(LocalDateTime.now());

        modelMap.addAttribute("standardDate", new Date());
        modelMap.addAttribute("localDateTime", LocalDateTime.now());
        modelMap.addAttribute("localDate", LocalDate.now());
        modelMap.addAttribute("timestamp", Instant.now());

        System.out.println("---------------------TEST STOP------------------------");
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
    public String postCountry(@ModelAttribute("userForm") @Valid RegistrationRequest request, BindingResult result, Model model){
        System.out.println("--------------------********************************-----------------------------");
//        System.out.println(registrationService.register(request));
//        Boolean isValidEmail = emailValidator.test(request.getEmail());
//        System.out.println(isValidEmail.toString());
        System.out.println("--------------------NAPIS TESTOWY--------------------------");
        System.out.println(request.toString());
        System.out.println(result.getModel().toString());
        if (result.hasErrors()) {
            return "home/registration";
        }

        try {
            registrationService.register(request);
        }catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
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

    @GetMapping("/test")
    public String getTest(){
        System.out.println("==============STRONA TESTOWA===================");
        return "home/test";
    }

}
