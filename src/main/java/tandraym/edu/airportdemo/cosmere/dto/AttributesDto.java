package tandraym.edu.airportdemo.cosmere.dto;

public record AttributesDto(
    AttributeValueDto strength,
    AttributeValueDto speed,
    AttributeValueDto intellect,
    AttributeValueDto willpower,
    AttributeValueDto awareness,
    AttributeValueDto presence
) {}
