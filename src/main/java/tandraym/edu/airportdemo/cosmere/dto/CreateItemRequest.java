package tandraym.edu.airportdemo.cosmere.dto;

import tandraym.edu.airportdemo.cosmere.domain.ItemType;

public record CreateItemRequest(
    String   name,
    ItemType type,
    String   itemKey,
    String   img,
    String   descriptionValue,
    String   descriptionShort,
    String   systemData
) {}
