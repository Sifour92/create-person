package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.EquipType;
import tandraym.edu.rpg.util.enums.weapon.EquipHand;
import tandraym.edu.rpg.util.enums.weapon.HoldType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equip {
    private EquipType type;
    private HoldType hold;
    private EquipHand hand;
}
