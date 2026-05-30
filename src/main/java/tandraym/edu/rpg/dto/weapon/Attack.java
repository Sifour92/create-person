package tandraym.edu.rpg.dto.weapon;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.weapon.AttackType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attack {
    private AttackType type;
    private Range range;
}
