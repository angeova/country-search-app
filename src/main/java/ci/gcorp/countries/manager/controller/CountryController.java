package ci.gcorp.countries.manager.controller;

import ci.gcorp.countries.manager.services.CountriesService;
import ci.gcorp.countries.manager.utils.contract.Response;
import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@RestController
public class CountryController {
    @Autowired
    CountriesService countriesService;

    private static final int BUTTONS_TO_SHOW = 5;

    @RequestMapping("/country/all")
    public ModelAndView findAll() {
        Response<CountryDto> response = countriesService.findAll();

        ModelAndView mav = new ModelAndView("country/list");
        mav.addObject("items", response.getItems());
        return mav;
    }

    @RequestMapping("/country/list")
    public ModelAndView findAll(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize    = size.orElse(10);

        Page<CountryDto> itemsPage = countriesService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        ModelAndView mav = new ModelAndView("country/list");

        int totalPages = itemsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                                                 .boxed()
                                                 .collect(Collectors.toList());
            mav.addObject("pageNumbers", pageNumbers);
        }
        mav.addObject("itemsPage", itemsPage);
        mav.addObject("selectedPageSize", pageSize);
        return mav;
    }

    @RequestMapping("/country/findByName/{name}")
    public ModelAndView findByName(@PathVariable("name") String name) {
        Response<CountryDto> response = countriesService.find("name", name, true);

        ModelAndView mav = null;
        if (!response.isHasError() && response.getItems() != null && !response.getItems().isEmpty()) {
            mav = new ModelAndView("country/detail");
            mav.addObject("item", response.getItems().get(0));
        } else {
            mav = new ModelAndView("index");
            mav.addObject("response", response);
        }
        return mav;
    }

    @RequestMapping("/country/find/{search}")
    public ModelAndView find(@PathVariable("search") String search) {
        Response<CountryDto> response = countriesService.find("name", search, false);

        ModelAndView mav = null;
        if (!response.isHasError() && response.getItems() != null && !response.getItems().isEmpty()) {
            mav = new ModelAndView("country/all");
            mav.addObject("items", response.getItems());
        } else {
            mav = new ModelAndView("index");
            mav.addObject("response", response);
        }
        return mav;
    }
}
