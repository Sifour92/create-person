package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.ItemUseType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Uses {
    private ItemUseType type;
    private Integer value = 1;
    private Integer max = 1;
    private String recharge;
}
