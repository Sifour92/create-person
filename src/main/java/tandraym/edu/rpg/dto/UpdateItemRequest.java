package tandraym.edu.rpg.dto;

/**
 * Request body for PUT /api/cosmere/items/{id}.
 * All fields are full-replacement (no partial PATCH semantics).
 */
public record UpdateItemRequest(
        String name,
        String type,
        String itemKey,
        String img,
        String descriptionValue,
        String descriptionShort,
        String systemData
) {}
