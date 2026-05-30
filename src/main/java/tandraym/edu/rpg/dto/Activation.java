package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.ActivationType;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Activation {
    private Cost cost;
    private List<Object> consume;

    private String flavor;
    private ActivationType type;

    /**
     * Можно "default", "none", null или skill id: hwp, lwp, agi...
     */
    private String skill;

    /**
     * Можно "default", "none", null или attribute id: str, spd...
     */
    private String attribute;

    private String modifierFormula;
    private Boolean plotDie;
    private Integer opportunity;
    private Integer complication;
    private Uses uses;
}
