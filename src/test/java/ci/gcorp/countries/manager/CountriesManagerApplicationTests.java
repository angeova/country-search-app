package ci.gcorp.countries.manager;

import ci.gcorp.countries.manager.services.CountriesService;
import ci.gcorp.countries.manager.utils.contract.Response;
import ci.gcorp.countries.manager.utils.dto.CountryDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesManagerApplicationTests {

    @Autowired
    CountriesService countriesService;

    @Test
    public void testGetOneCountrySuccess() {
        Response<CountryDto> response = countriesService.find("name", "aruba", true);

        // Verify request succeed
        Assert.assertEquals(false, response.isHasError());
        // Verify we got one data
        Assert.assertEquals(true, response.getItems() != null && !response.getItems().isEmpty() && response.getItems().size() == 2);
    }

    @Test
    public void testGetCountriesByCapitalSuccess() {
        Response<CountryDto> response = countriesService.find("capital", "yamoussoukro", false);

        // Verify request succeed
        Assert.assertEquals(false, response.isHasError());
        // Verify we got one data
        Assert.assertTrue(response.getItems() != null && !response.getItems().isEmpty() && response.getItems().size() == 1);
        // Verify this country is right
        Assert.assertEquals("Côte d'Ivoire", response.getItems().get(0).getName());
    }

    @Test
    public void testGetCountriesByLangSuccess() {
        Response<CountryDto> response = countriesService.find("lang", "fr", false);

        // Verify request succeed
        Assert.assertEquals(false, response.isHasError());
        // Verify we got many datas
        Assert.assertTrue(response.getItems() != null && !response.getItems().isEmpty() && response.getItems().size() > 1);
        // Verify these countries have correct language
        Assert.assertTrue(response.getItems().stream().anyMatch(c -> c.getName().equals("Côte d'Ivoire")));
        Assert.assertTrue(response.getItems().stream().anyMatch(c -> c.getName().equals("France")));
        Assert.assertTrue(response.getItems().stream().anyMatch(c -> c.getName().equals("Lebanon")));
        Assert.assertEquals(false, response.getItems().stream().anyMatch(c -> c.getName().equals("England")));
        Assert.assertEquals(false, response.getItems().stream().anyMatch(c -> c.getName().equals("Ghana")));
    }
}
