package tandraym.edu.airportdemo.cosmere.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(schema = "cosmere", name = "character_skills")
@Data
public class CharacterSkill {

    @EmbeddedId
    private CharacterSkillId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("characterId")
    @JoinColumn(name = "character_id")
    @ToString.Exclude
    private PlayerCharacter character;

    private int     rank        = 0;
    private Integer modOverride;
    private int     modBonus    = 0;

    // false для магических навыков (adh, grv, ill...) — разблокируются через путь
    @Column(nullable = false)
    private boolean unlocked    = true;
}
