package tandraym.edu.rpg.dto.weapon;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Range {
    private Integer value = 5;
    private Integer longRange = 5;

    private String unit = "ft";

    @com.fasterxml.jackson.annotation.JsonProperty("long")
    public Integer getLong() {
        return longRange;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("long")
    public void setLong(Integer longRange) {
        this.longRange = longRange;
    }
}
