package tandraym.edu.rpg.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tandraym.edu.rpg.dto.ItemDataModel;
import tandraym.edu.rpg.dto.WeaponItemDataModel;
import tandraym.edu.rpg.util.enums.ItemType;

import java.util.List;
import java.util.Map;

/**
 * Request body for POST /api/cosmere/items.
 *
 * <p>{@code type} must be one of the {@link tandraym.edu.rpg.util.enums.ItemType} values
 * (case-insensitive): weapon, armor, fabrial, gear, sphere, travel, etc.</p>
 *
 * <p>{@code systemData} is the raw FVTT {@code system} JSON — can be null
 * if creating a simple item without game-mechanics data.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemRequest {

    @NotBlank
    private String name;

    @NotNull
    private ItemType type;

    private String img;

    @Valid
    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = WeaponItemDataModel.class, name = "weapon"),
            //@JsonSubTypes.Type(value = ArmorItemDataModel.class,  name = "armor"),
            // остальные по мере добавления
    })
    private ItemDataModel system;

    private List<Object> effects;
    private Map<String, Object> flags;

}
