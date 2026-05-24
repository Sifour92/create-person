package tandraym.edu.rpg.dto;

/**
 * Request body for POST /api/cosmere/items.
 *
 * <p>{@code type} must be one of the {@link tandraym.edu.rpg.domain.ItemType} values
 * (case-insensitive): weapon, armor, fabrial, gear, sphere, travel, etc.</p>
 *
 * <p>{@code systemData} is the raw FVTT {@code system} JSON — can be null
 * if creating a simple item without game-mechanics data.</p>
 */
public record CreateItemRequest(
        String name,
        String type,
        String itemKey,
        String img,
        String descriptionValue,
        String descriptionShort,
        String systemData
) {}
