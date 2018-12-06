package ci.gcorp.countries.manager.utils.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Data
@ToString
public class CurrencyDto {
    private String code;
    private String name;
    private String symbol;
}
