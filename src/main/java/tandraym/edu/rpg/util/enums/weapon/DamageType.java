package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DamageType {
    ENERGY("energy"),
    IMPACT("impact"),
    KEEN("keen"),
    SPIRIT("spirit"),
    VITAL("vital"),
    HEALING("heal");

    private final String value;

    DamageType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
