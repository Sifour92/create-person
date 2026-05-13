package tandraym.edu.airportdemo.cosmere.domain.embedded;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Resources {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value",       column = @Column(name = "res_hea_value")),
        @AttributeOverride(name = "maxOverride", column = @Column(name = "res_hea_max_override")),
        @AttributeOverride(name = "bonus",       column = @Column(name = "res_hea_bonus"))
    })
    private ResourceValue health = new ResourceValue(0, null, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value",       column = @Column(name = "res_foc_value")),
        @AttributeOverride(name = "maxOverride", column = @Column(name = "res_foc_max_override")),
        @AttributeOverride(name = "bonus",       column = @Column(name = "res_foc_bonus"))
    })
    private ResourceValue focus = new ResourceValue(0, null, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value",       column = @Column(name = "res_inv_value")),
        @AttributeOverride(name = "maxOverride", column = @Column(name = "res_inv_max_override")),
        @AttributeOverride(name = "bonus",       column = @Column(name = "res_inv_bonus"))
    })
    private ResourceValue investiture = new ResourceValue(0, null, 0);
}
