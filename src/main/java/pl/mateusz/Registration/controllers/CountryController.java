package pl.mateusz.Registration.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import pl.mateusz.Registration.models.CountryModel;
import pl.mateusz.Registration.models.repositories.CountryRepository;

import java.util.List;

@AllArgsConstructor
@Controller
public class CountryController {

    private CountryRepository countryRepository;

    @GetMapping("country")
    public String getindex(ModelMap modelMap) {
        System.out.println("-------------------Będą PAńSTWA----------------------");
        List<CountryModel> countries = countryRepository.findAll();
        modelMap.addAttribute("countries", countries);

        return "country/index";
    }
}
