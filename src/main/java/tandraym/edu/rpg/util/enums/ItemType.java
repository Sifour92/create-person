package tandraym.edu.rpg.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * FVTT Cosmere RPG item types.
 * Values match the "type" field in FoundryVTT item JSON (case-insensitive).
 */
public enum ItemType {
    WEAPON("weapon"),
    ARMOR("armor"),
    EQUIPMENT("equipment"),
    LOOT("loot"),
    ANCESTRY("ancestry"),
    CULTURE("culture"),
    PATH("path"),
    TALENT("talent"),
    TRAIT("trait"),
    ACTION("action"),
    INJURY("injury"),
    CONNECTION("connection"),
    GOAL("goal"),
    POWER("power"),
    TALENT_TREE("talent_tree");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}