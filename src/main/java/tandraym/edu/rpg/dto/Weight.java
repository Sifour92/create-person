package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weight {
    private Number value = 0;
    private String unit = "lb";
}
