package ci.gcorp.countries.manager.utils.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Data
@ToString
public class CountryDto {
    private String                name;
    private List<String>          topLevelDomain;
    private String                alpha2Code;
    private String                alpha3Code;
    private List<String>          callingCodes;
    private String                capital;
    private List<String>          altSpellings;
    private String                region;
    private String                subregion;
    private Integer               population;
    private List<Float>           latlng;
    private String                demonym;
    private Integer               area;
    private Integer               gini;
    private List<String>          timezones;
    private List<String>          borders;
    private String                nativeName;
    private String                numericCode;
    private List<CurrencyDto>     currencies;
    private List<LanguageDto>     languages;
    private TranslationDto        translations;
    private String                flag;
    private List<RegionalBlocDto> regionalBlocs;
    private String                cioc;
}
