package tandraym.edu.airportdemo.cosmere.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceValue {
    private int value    = 0;
    private Integer maxOverride;
    private int bonus    = 0;
}
