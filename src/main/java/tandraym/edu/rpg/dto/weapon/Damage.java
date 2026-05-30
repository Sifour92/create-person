package tandraym.edu.rpg.dto.weapon;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.weapon.DamageType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Damage {
    private String skill;
    private String attribute;
    private String formula;
    private DamageType type;
    private String grazeOverrideFormula;
}
