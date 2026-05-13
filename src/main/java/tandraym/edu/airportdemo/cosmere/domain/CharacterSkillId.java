package tandraym.edu.airportdemo.cosmere.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSkillId implements Serializable {
    private UUID   characterId;
    private String skillKey;
}
