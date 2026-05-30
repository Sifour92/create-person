package tandraym.edu.rpg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import tandraym.edu.rpg.dto.weapon.Attack;
import tandraym.edu.rpg.dto.weapon.Damage;
import tandraym.edu.rpg.util.enums.weapon.WeaponTraitId;
import tandraym.edu.rpg.util.enums.weapon.WeaponType;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeaponItemDataModel implements ItemDataModel {
    private String id;
    private WeaponType type;
    private Integer quantity = 1;
    private Boolean equipped = false;
    private Boolean alwaysEquipped = false;
    private Boolean expertise = false;

    private Description description;
    private Equip equip;
    private Activation activation;
    private Damage damage;
    private Map<WeaponTraitId, Trait> traits;
    private Weight weight;
    private Price price;
    private Attack attack ;
    private Map<String, Object> events;
    private List<String> linkedSkills;
    private Map<String, Object> relationships;
}