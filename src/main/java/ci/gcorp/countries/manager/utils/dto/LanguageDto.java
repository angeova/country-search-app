package ci.gcorp.countries.manager.utils.dto;

import lombok.Data;
import lombok.ToString;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Data
@ToString
public class LanguageDto {
    private String name;
    private String nativeName;
    private String iso639_1;
    private String iso639_2;
}
