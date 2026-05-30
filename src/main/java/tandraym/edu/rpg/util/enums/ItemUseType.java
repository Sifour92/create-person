package tandraym.edu.rpg.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ItemUseType {
    USE("use"),
    CHARGE("charge");

    private final String value;

    ItemUseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}