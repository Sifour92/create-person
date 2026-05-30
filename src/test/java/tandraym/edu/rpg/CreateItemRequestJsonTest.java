package tandraym.edu.rpg;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import tandraym.edu.rpg.dto.WeaponItemDataModel;
import tandraym.edu.rpg.dto.request.CreateItemRequest;
import tandraym.edu.rpg.dto.weapon.Damage;
import tandraym.edu.rpg.util.enums.ItemType;
import tandraym.edu.rpg.util.enums.weapon.DamageType;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("CreateItemRequest polymorphic (de)serialization")
class CreateItemRequestJsonTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Nested
    @DisplayName("Deserialization picks the right subtype by 'type'")
    class Deserialization {

        @Test
        @DisplayName("type=weapon -> WeaponItemDataModel")
        void deserializesWeapon() throws Exception {
            String json = """
                {
                  "name": "Sidesword",
                  "type": "weapon",
                  "img": "icons/weapons/sword.webp",
                  "system": {
                    "id": "abc",
                    "damage": { "formula": "1d6", "type": "impact" }
                  },
                  "effects": [],
                  "flags": {}
                }
                """;

            CreateItemRequest request = mapper.readValue(json, CreateItemRequest.class);

            assertThat(request.getType()).isEqualTo(ItemType.WEAPON);
            assertThat(request.getSystem()).isInstanceOf(WeaponItemDataModel.class);

            WeaponItemDataModel weapon = (WeaponItemDataModel) request.getSystem();
            assertThat(weapon.getId()).isEqualTo("abc");
            assertThat(weapon.getDamage().getFormula()).isEqualTo("1d6");
            assertThat(weapon.getDamage().getType())
                    .isEqualTo(DamageType.IMPACT);
        }

//        @Test
//        @DisplayName("type=armor -> ArmorItemDataModel")
//        void deserializesArmor() throws Exception {
//            String json = """
//                {
//                  "name": "Breastplate",
//                  "type": "armor",
//                  "img": "icons/armor/plate.webp",
//                  "system": { "id": "def" },
//                  "effects": [],
//                  "flags": {}
//                }
//                """;
//
//            CreateItemRequest request = mapper.readValue(json, CreateItemRequest.class);
//
//            assertThat(request.type).isEqualTo(ItemType.ARMOR);
//            assertThat(request.system).isInstanceOf(ArmorItemDataModel.class);
//        }
    }

    @Nested
    @DisplayName("Serialization keeps the contract")
    class Serialization {

        @Test
        @DisplayName("weapon survives a round-trip")
        void weaponRoundTrip() throws Exception {
            WeaponItemDataModel data = new WeaponItemDataModel();
            data.setId("abc");
            data.setDamage(Damage.builder().formula("1d6").build());

            CreateItemRequest original = new CreateItemRequest();
            original.setName("Sidesword");
            original.setType(ItemType.WEAPON);
            original.setImg("icons/weapons/sword.webp");
            original.setSystem(data);
            original.setEffects(List.of());
            original.setFlags(Map.of());

            String json = mapper.writeValueAsString(original);
            CreateItemRequest back = mapper.readValue(json, CreateItemRequest.class);

            assertThat(json).contains("\"type\":\"weapon\"");
            assertThat(back.getSystem()).isInstanceOf(WeaponItemDataModel.class);
            assertThat(((WeaponItemDataModel) back.getSystem()).getDamage().getFormula())
                    .isEqualTo("1d6");
        }
    }
}