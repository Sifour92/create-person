package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Denomination {
    private String primary = "none";
    private String secondary = "none";
}
