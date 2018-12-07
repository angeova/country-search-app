package ci.gcorp.countries.manager.controller;

import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@RestController
public class HomeController {

    @GetMapping("/")
    ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("response", null);
        mav.addObject("search", new CountryDto());
        return mav;
    }
}
