package tandraym.edu.airportdemo.cosmere.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "cosmere", name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    private String itemKey;
    private String img;

    @Column(columnDefinition = "TEXT")
    private String descriptionValue;

    @Column(columnDefinition = "TEXT")
    private String descriptionShort;

    // JSONB: хранит специфичные для типа данные (events, activation, advancement...)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String systemData;

    private LocalDateTime createdAt = LocalDateTime.now();
}
