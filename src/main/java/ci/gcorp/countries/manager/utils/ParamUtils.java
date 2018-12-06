package ci.gcorp.countries.manager.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Getter
@Component
public class ParamUtils {
    @Value("${country.api.endpoint.base}")
    private String countryApiEndpointBase;
}
