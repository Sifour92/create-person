package tandraym.edu.airportdemo.cosmere.domain.embedded;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Attributes {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_str_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_str_bonus"))
    })
    private AttributeValue strength = new AttributeValue(1, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_spd_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_spd_bonus"))
    })
    private AttributeValue speed = new AttributeValue(1, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_int_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_int_bonus"))
    })
    private AttributeValue intellect = new AttributeValue(1, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_wil_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_wil_bonus"))
    })
    private AttributeValue willpower = new AttributeValue(1, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_awa_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_awa_bonus"))
    })
    private AttributeValue awareness = new AttributeValue(1, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "value", column = @Column(name = "attr_pre_value")),
        @AttributeOverride(name = "bonus", column = @Column(name = "attr_pre_bonus"))
    })
    private AttributeValue presence = new AttributeValue(1, 0);
}
