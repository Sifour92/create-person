package tandraym.edu.airportdemo.cosmere.domain.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeValue {
    private int value = 1;
    private int bonus = 0;
}
