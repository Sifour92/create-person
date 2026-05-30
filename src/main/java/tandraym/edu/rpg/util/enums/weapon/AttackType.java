package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AttackType {
    MELEE("melee"),
    RANGED("ranged");

    private final String value;

    AttackType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}