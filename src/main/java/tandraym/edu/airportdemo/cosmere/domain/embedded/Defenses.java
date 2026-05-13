package tandraym.edu.airportdemo.cosmere.domain.embedded;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Defenses {

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "override", column = @Column(name = "def_phy_override")),
        @AttributeOverride(name = "bonus",    column = @Column(name = "def_phy_bonus"))
    })
    private DefenseValue physical = new DefenseValue(null, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "override", column = @Column(name = "def_cog_override")),
        @AttributeOverride(name = "bonus",    column = @Column(name = "def_cog_bonus"))
    })
    private DefenseValue cognitive = new DefenseValue(null, 0);

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "override", column = @Column(name = "def_spi_override")),
        @AttributeOverride(name = "bonus",    column = @Column(name = "def_spi_bonus"))
    })
    private DefenseValue spiritual = new DefenseValue(null, 0);
}
