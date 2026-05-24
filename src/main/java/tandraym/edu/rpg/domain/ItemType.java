package tandraym.edu.rpg.domain;

/**
 * FVTT Cosmere RPG item types.
 * Values match the "type" field in FoundryVTT item JSON (case-insensitive).
 */
public enum ItemType {

    // ── Inventory / physical items (items.html) ──────────────────────────
    WEAPON,
    ARMOR,
    FABRIAL,
    GEAR,
    SPHERE,
    TRAVEL,

    // ── Character-sheet elements (character-sheet.html) ──────────────────
    ANCESTRY,
    CULTURE,
    PATH,
    ACTION,

    // ── Other FVTT types ─────────────────────────────────────────────────
    EQUIPMENT,
    LOOT
}
