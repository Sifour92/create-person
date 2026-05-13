package tandraym.edu.airportdemo.cosmere.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record PlayerCharacterDto(
    UUID         id,
    String       name,
    int          tier,
    String       size,
    String       img,
    AttributesDto attributes,
    ResourcesDto  resources,
    DefensesDto   defenses,
    Map<String, SkillDto> skills,
    List<ItemDto> items
) {}
