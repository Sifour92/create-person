package tandraym.edu.airportdemo.cosmere.service;

import org.springframework.stereotype.Component;
import tandraym.edu.airportdemo.cosmere.domain.CharacterSkill;
import tandraym.edu.airportdemo.cosmere.domain.Item;
import tandraym.edu.airportdemo.cosmere.domain.PlayerCharacter;
import tandraym.edu.airportdemo.cosmere.domain.embedded.AttributeValue;
import tandraym.edu.airportdemo.cosmere.domain.embedded.DefenseValue;
import tandraym.edu.airportdemo.cosmere.domain.embedded.ResourceValue;
import tandraym.edu.airportdemo.cosmere.dto.*;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CharacterMapper {

    public PlayerCharacterDto toDto(PlayerCharacter c) {
        return new PlayerCharacterDto(
            c.getId(),
            c.getName(),
            c.getTier(),
            c.getSize(),
            c.getImg(),
            toAttributesDto(c),
            toResourcesDto(c),
            toDefensesDto(c),
            toSkillsMap(c),
            c.getItems().stream().map(this::toItemDto).toList()
        );
    }

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
            item.getId(),
            item.getName(),
            item.getType(),
            item.getItemKey(),
            item.getImg(),
            item.getDescriptionShort()
        );
    }

    private AttributesDto toAttributesDto(PlayerCharacter c) {
        var a = c.getAttributes();
        return new AttributesDto(
            toAttrDto(a.getStrength()),
            toAttrDto(a.getSpeed()),
            toAttrDto(a.getIntellect()),
            toAttrDto(a.getWillpower()),
            toAttrDto(a.getAwareness()),
            toAttrDto(a.getPresence())
        );
    }

    private AttributeValueDto toAttrDto(AttributeValue v) {
        return new AttributeValueDto(v.getValue(), v.getBonus());
    }

    private ResourcesDto toResourcesDto(PlayerCharacter c) {
        var r = c.getResources();
        return new ResourcesDto(
            toResDto(r.getHealth()),
            toResDto(r.getFocus()),
            toResDto(r.getInvestiture())
        );
    }

    private ResourceValueDto toResDto(ResourceValue v) {
        return new ResourceValueDto(v.getValue(), v.getMaxOverride(), v.getBonus());
    }

    private DefensesDto toDefensesDto(PlayerCharacter c) {
        var d = c.getDefenses();
        return new DefensesDto(
            toDefDto(d.getPhysical()),
            toDefDto(d.getCognitive()),
            toDefDto(d.getSpiritual())
        );
    }

    private DefenseValueDto toDefDto(DefenseValue v) {
        return new DefenseValueDto(v.getOverride(), v.getBonus());
    }

    private Map<String, SkillDto> toSkillsMap(PlayerCharacter c) {
        return c.getSkills().stream().collect(Collectors.toMap(
            s -> s.getId().getSkillKey(),
            s -> new SkillDto(s.getRank(), s.getModOverride(), s.getModBonus(), s.isUnlocked())
        ));
    }
}
