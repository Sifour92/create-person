package tandraym.edu.rpg.util.enums.weapon;

import com.fasterxml.jackson.annotation.JsonValue;

public enum WeaponTraitId {
    CUMBERSOME("cumbersome"),
    DANGEROUS("dangerous"),
    DEADLY("deadly"),
    DEFENSIVE("defensive"),
    DISCREET("discreet"),
    INDIRECT("indirect"),
    LOADED("loaded"),
    MOMENTUM("momentum"),
    OFFHAND("offhand"),
    PIERCE("pierce"),
    QUICKDRAW("quickdraw"),
    THROWN("thrown"),
    TWO_HANDED("two_handed"),
    UNIQUE("unique"),
    FRAGILE("fragile"),
    REACH("reach");

    private final String value;

    WeaponTraitId(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}