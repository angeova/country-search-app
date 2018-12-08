package ci.gcorp.countries.manager.controller;

import ci.gcorp.countries.manager.services.CountriesService;
import ci.gcorp.countries.manager.utils.contract.Response;
import ci.gcorp.countries.manager.utils.contract.Status;
import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @GetMapping("/country/list")
    public ModelAndView findAll(@RequestParam Optional<String> name, @RequestParam Optional<String> fieldName, @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {
        return findData(name.orElse(null), fieldName.orElse(null), page, size);
    }

    @GetMapping("/country/findByName/{name}")
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

    @PostMapping("/country/find")
    public ModelAndView find(@RequestParam String name, @RequestParam String fieldName, @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size) {
        return findData(name, fieldName, page, size);
    }

    private ModelAndView findData(String name, String fieldName, Optional<Integer> page, Optional<Integer> size) {
        int                 currentPage = page.orElse(1);
        int                 pageSize    = size.orElse(10);
        Map<String, Object> errorMap    = new HashMap<>();
        // find all is name is null or empty
        Page<CountryDto>    itemsPage   = (name == null || name.isEmpty()) ? countriesService.findAllPaginated(PageRequest.of(currentPage - 1, pageSize)) : countriesService.findByFieldPaginated(PageRequest.of(currentPage - 1, pageSize), fieldName, name, false, errorMap);
        ModelAndView        mav         = null;
        // open country list page
        if (!errorMap.containsKey("status")) {
            mav = new ModelAndView("country/list");
            int totalPages = itemsPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                                                     .boxed()
                                                     .collect(Collectors.toList());
                mav.addObject("pageNumbers", pageNumbers);
            }
            mav.addObject("itemsPage", itemsPage);
            mav.addObject("selectedPageSize", pageSize);
            mav.addObject("name", name);
            mav.addObject("fieldName", fieldName);
        } else {
            mav = new ModelAndView("index");
            Response response = new Response();
            response.setStatus((Status) errorMap.get("status"));
            response.setHasError(true);
            mav.addObject("response", response);
            mav.addObject("search", new CountryDto());
        }
        return mav;
    }
}
