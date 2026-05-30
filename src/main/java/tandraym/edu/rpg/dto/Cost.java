package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.util.enums.ActionCostType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cost {
    private Integer value;
    private ActionCostType type;
}
