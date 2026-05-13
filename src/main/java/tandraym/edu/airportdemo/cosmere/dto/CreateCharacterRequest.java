package tandraym.edu.airportdemo.cosmere.dto;

public record CreateCharacterRequest(
    String name,
    int    tier,
    String size,
    String img
) {
    public CreateCharacterRequest {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (tier < 1 || tier > 5)          throw new IllegalArgumentException("tier must be 1-5");
        if (size == null)                   size = "medium";
    }
}
