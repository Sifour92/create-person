package tandraym.edu.rpg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trait {
    private Boolean defaultActive = false;
    private Integer defaultValue;
    private Integer value;
    private Boolean active;
    private TraitExpertise expertise;

    private static Trait active() {
        Trait t = new Trait();
        t.defaultActive = true;
        return t;
    }

    private static Trait inactive() {
        return new Trait();
    }

    private static Trait expertGain() {
        Trait t = new Trait();
        t.defaultActive = false;
        t.expertise = new TraitExpertise();
        t.expertise.toggleActive = true;
        return t;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class TraitExpertise {
        private Boolean toggleActive = false;
        private Integer value = null;
    }

}
