package tandraym.edu.rpg.dto;

import java.util.UUID;

/**
 * Read-only projection of an Item.
 *
 * <p>{@code type} is returned in lowercase to match FVTT conventions
 * (e.g. "weapon", "armor") — keeps the frontend mapping trivial.</p>
 *
 * <p>{@code systemData} is the raw FVTT {@code system} JSON string.
 * The frontend parses it to extract damage, traits, weight, price, etc.</p>
 */
public record ItemDto(
        UUID   id,
        String name,
        String type,            // lowercase: "weapon", "armor", …
        String itemKey,
        String img,
        String descriptionShort,
        String systemData       // raw FVTT system JSON
) {}
