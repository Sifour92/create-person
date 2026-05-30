package tandraym.edu.rpg.dto;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Price {
    private Number value = 0;
    private String currency = "none";
    private Denomination denomination;

    /**
     * Можно не задавать, но Foundry export обычно содержит.
     */
    private String unit = "none.none";
}