package tandraym.edu.rpg.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tandraym.edu.rpg.util.enums.ItemType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Cosmere RPG item — universal entity for all FVTT item types.
 *
 * <p>{@code systemData} stores the full FVTT {@code system} object as PostgreSQL
 * JSONB. Hibernate сериализует Map через Jackson автоматически. Колонка имеет
 * GIN-индекс (миграция V2), так что можно искать по полям внутри JSON:</p>
 * <pre>
 *   WHERE system_data-&gt;'traits' ? 'thrown'              — все weapons с thrown
 *   WHERE system_data-&gt;'damage'-&gt;&gt;'type' = 'keen'   — все с keen damage
 *   WHERE system_data @&gt; '{"type":"heavy_wpn"}'         — все heavy_wpn
 * </pre>
 */
@Entity
@Table(name = "items")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ItemType type;

    /** FVTT system.id (e.g. "axe", "shardblade") */
    @Column(name = "item_key", length = 100)
    private String itemKey;

    /** Path to item icon/image */
    @Column(columnDefinition = "TEXT")
    private String img;

    /** Full HTML description (system.description.value) */
    @Column(name = "description_value", columnDefinition = "TEXT")
    private String descriptionValue;

    /** Short one-line subtitle shown in the table */
    @Column(name = "description_short", length = 1000)
    private String descriptionShort;

    /**
     * Full FVTT {@code system} object as JSONB. Hibernate use Jackson to
     * marshal/unmarshal Map ↔ jsonb автоматически.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "system_data", columnDefinition = "jsonb")
    private Map<String, Object> systemData;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
