package tandraym.edu.airportdemo.cosmere.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import tandraym.edu.airportdemo.cosmere.domain.embedded.Attributes;
import tandraym.edu.airportdemo.cosmere.domain.embedded.Defenses;
import tandraym.edu.airportdemo.cosmere.domain.embedded.Resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "cosmere", name = "characters")
@Data
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private int    tier = 1;
    private String size = "medium";
    private String img;

    // Embedded value objects — плоские колонки в той же таблице
    @Embedded
    private Attributes attributes = new Attributes();

    @Embedded
    private Resources resources = new Resources();

    @Embedded
    private Defenses defenses = new Defenses();

    // Навыки — отдельная таблица, orphanRemoval удаляет их вместе с персонажем
    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CharacterSkill> skills = new ArrayList<>();

    // Items (ancestry, culture, paths, actions) — M:N через join table
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        schema = "cosmere",
        name = "character_items",
        joinColumns        = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void addItem(Item item) {
        items.add(item);
        updatedAt = LocalDateTime.now();
    }

    public void removeItem(UUID itemId) {
        items.removeIf(i -> i.getId().equals(itemId));
        updatedAt = LocalDateTime.now();
    }

    public List<Item> getItemsByType(ItemType type) {
        return items.stream().filter(i -> i.getType() == type).toList();
    }
}
