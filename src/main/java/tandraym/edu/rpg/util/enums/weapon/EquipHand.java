package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EquipHand {
    MAIN_HAND("main_hand"),
    OFF_HAND("off_hand");

    private final String value;

    EquipHand(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}