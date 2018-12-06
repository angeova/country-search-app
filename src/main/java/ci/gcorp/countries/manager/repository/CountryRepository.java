package ci.gcorp.countries.manager.repository;

import ci.gcorp.countries.manager.utils.ParamUtils;
import ci.gcorp.countries.manager.utils.RestCountryUtils;
import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Component
public class CountryRepository {
    RestTemplate restTemplate;
    @Autowired
    ParamUtils paramUtils;

    final String ALL = "/all";


    public CountryRepository() {
        restTemplate = new RestTemplate();
    }

    /**
     * find all
     * @return
     */
    public List<CountryDto> findAll() {
        String url = paramUtils.getCountryApiEndpointBase() + ALL;

        ResponseEntity<List<CountryDto>> serviceResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryDto>>() {});
        return serviceResponse.getBody();
    }

    /**
     * find by fieldName
     * @param fieldName
     * @param value
     * @param isFullText
     * @return
     * @throws Exception
     */
    public List<CountryDto> find(String fieldName, String value, boolean isFullText) throws Exception {
        if (!RestCountryUtils.isValidFieldName(fieldName)){
            throw new Exception("Unknow fieldName: " + fieldName);
        }
        String url = paramUtils.getCountryApiEndpointBase() + "/" + fieldName + "/" + value;
        if (isFullText){
            url += "?fullText=true";
        }

        ResponseEntity<List<CountryDto>> serviceResponse = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CountryDto>>() {});
        return serviceResponse.getBody();
    }

}
