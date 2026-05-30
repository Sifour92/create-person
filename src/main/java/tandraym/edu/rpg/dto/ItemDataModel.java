package tandraym.edu.rpg.dto;

/**
 * Маркерный интерфейс для system-данных предмета.
 *
 * <p>Полиморфизм настроен НЕ здесь, а на поле {@code system}
 * в {@link tandraym.edu.rpg.dto.request.CreateItemRequest} — потому что
 * {@code @JsonTypeInfo(As.EXTERNAL_PROPERTY)} работает только на уровне
 * свойства (внешний дискриминатор {@code type} лежит в содержащем POJO),
 * а не на уровне типа.</p>
 */
public interface ItemDataModel {}