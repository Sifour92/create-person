package tandraym.edu.rpg.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivationType {
    NONE("none"),
    UTILITY("utility"),
    SKILL_TEST("skill_test");

    private final String value;

    ActivationType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}