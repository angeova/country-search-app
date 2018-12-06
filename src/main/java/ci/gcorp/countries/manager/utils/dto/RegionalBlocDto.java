package ci.gcorp.countries.manager.utils.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by geovani.anoman on 12/4/18.
 */
@Data
@ToString
public class RegionalBlocDto {
    private String       name;
    private String       acronym;
    private List<String> otherAcronyms;
    private List<String> otherNames;
}
