package tandraym.edu.airportdemo.cosmere.dto;

import tandraym.edu.airportdemo.cosmere.domain.ItemType;

import java.util.UUID;

public record ItemDto(
    UUID     id,
    String   name,
    ItemType type,
    String   itemKey,
    String   img,
    String   descriptionShort
) {}
