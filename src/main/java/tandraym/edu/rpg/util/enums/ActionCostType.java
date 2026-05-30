package tandraym.edu.rpg.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ActionCostType {
    ACTION("act"),
    REACTION("rea"),
    FREE_ACTION("fre"),
    SPECIAL("spe"),
    NONE("none");

    private final String value;

    ActionCostType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}