package ci.gcorp.countries.manager.services;

import ci.gcorp.countries.manager.repository.CountryRepository;
import ci.gcorp.countries.manager.utils.contract.Response;
import ci.gcorp.countries.manager.utils.contract.Status;
import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Service
public class CountriesService {
    Response<CountryDto> response;
    @Autowired
    CountryRepository countryRepository;

    public Response<CountryDto> findAll() {
        response = new Response<CountryDto>();
        try {
            List<CountryDto> items = countryRepository.findAll();
            response.setItems(items);
        } catch (Exception e) {
            Status status = new Status();
            status.setMessage(e.getMessage());
            response.setHasError(true);
            response.setStatus(status);
        }
        return response;
    }

    public Page<CountryDto> findAllPaginated(Pageable pageable) {
        int              pageSize    = pageable.getPageSize();
        int              currentPage = pageable.getPageNumber();
        int              startItem   = currentPage * pageSize;
        List<CountryDto> list;

        List<CountryDto> items = countryRepository.findAll();
        if (items != null && items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }
        Page<CountryDto> itemsPage = new PageImpl<CountryDto>(list, PageRequest.of(currentPage, pageSize), items.size());
        return itemsPage;
    }

    public Page<CountryDto> findByFieldPaginated(Pageable pageable, String fieldName, String value, boolean isFullText, @NotNull Map<String, Object> errorMap) {
        int              pageSize    = pageable.getPageSize();
        int              currentPage = pageable.getPageNumber();
        int              startItem   = currentPage * pageSize;
        Page<CountryDto> itemsPage   = null;
        try {
            List<CountryDto> list;
            List<CountryDto> items = countryRepository.find(fieldName, value, isFullText);
            if (items != null && items.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, items.size());
                list = items.subList(startItem, toIndex);
            }
            itemsPage = new PageImpl<CountryDto>(list, PageRequest.of(currentPage, pageSize), items.size());
        } catch (Exception e) {
            Status status = new Status();
            status.setMessage(e.getMessage());
            if (status.getMessage().equalsIgnoreCase("404 null")) {
                status.setMessage("Empty list");
            }
            errorMap.put("status", status);
        }
        return itemsPage;
    }

    public Response<CountryDto> find(String fieldName, String value, boolean isFullText) {
        response = new Response<CountryDto>();
        try {
            List<CountryDto> items = countryRepository.find(fieldName, value, isFullText);
            response.setItems(items);
        } catch (Exception e) {
            Status status = new Status();
            status.setMessage(e.getMessage());
            if (status.getMessage().equalsIgnoreCase("404 null")) {
                status.setMessage("Empty list");
            }
            response.setHasError(true);
            response.setStatus(status);
        }
        return response;
    }
}
