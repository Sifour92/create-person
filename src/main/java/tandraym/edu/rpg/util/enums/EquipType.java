package tandraym.edu.rpg.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EquipType {
    HOLD("hold");

    private final String value;

    EquipType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}