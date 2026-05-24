package tandraym.edu.rpg.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Cosmere RPG item — universal entity for all FVTT item types.
 *
 * <p>{@code systemData} stores the raw FVTT {@code system} JSON object as text,
 * preserving the full game-mechanics payload (damage, traits, weight, price, …).
 * The frontend reads and writes this blob as-is.</p>
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
     * Full FVTT {@code system} object serialized as JSON string.
     * Contains damage, traits, weight, price, activation, attack, etc.
     */
    @Column(name = "system_data", columnDefinition = "TEXT")
    private String systemData;

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
