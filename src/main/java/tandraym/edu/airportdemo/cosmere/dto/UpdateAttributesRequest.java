package tandraym.edu.airportdemo.cosmere.dto;

public record UpdateAttributesRequest(
    Integer strength,
    Integer speed,
    Integer intellect,
    Integer willpower,
    Integer awareness,
    Integer presence
) {}
