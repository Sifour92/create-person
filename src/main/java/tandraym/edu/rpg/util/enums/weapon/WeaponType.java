package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WeaponType {
    LIGHT_WEAPON("light_wpn"),
    HEAVY_WEAPON("heavy_wpn"),
    SPECIAL_WEAPON("special_wpn");

    private final String value;

    WeaponType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}