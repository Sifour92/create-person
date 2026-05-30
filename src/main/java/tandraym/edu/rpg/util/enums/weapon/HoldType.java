package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HoldType {
    ONE_HANDED("one_handed"),
    TWO_HANDED("two_handed");

    private final String value;

    HoldType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}