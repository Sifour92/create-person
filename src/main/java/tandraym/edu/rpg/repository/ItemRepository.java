package tandraym.edu.rpg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tandraym.edu.rpg.domain.Item;
import tandraym.edu.rpg.domain.ItemType;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    // ── Filtered by type only ─────────────────────────────────────────────

    Page<Item> findByType(ItemType type, Pageable pageable);

    // ── Text search only (name OR descriptionShort) ───────────────────────

    @Query("""
            SELECT i FROM Item i
            WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR (i.descriptionShort IS NOT NULL
                   AND LOWER(i.descriptionShort) LIKE LOWER(CONCAT('%', :search, '%')))
            """)
    Page<Item> findBySearch(@Param("search") String search, Pageable pageable);

    // ── Type + text search ────────────────────────────────────────────────

    @Query("""
            SELECT i FROM Item i
            WHERE i.type = :type
              AND (LOWER(i.name) LIKE LOWER(CONCAT('%', :search, '%'))
                   OR (i.descriptionShort IS NOT NULL
                       AND LOWER(i.descriptionShort) LIKE LOWER(CONCAT('%', :search, '%'))))
            """)
    Page<Item> findByTypeAndSearch(
            @Param("type") ItemType type,
            @Param("search") String search,
            Pageable pageable
    );

    // ── Global type counts (for sidebar / stat-chips) ─────────────────────

    @Query("SELECT i.type, COUNT(i) FROM Item i GROUP BY i.type")
    List<Object[]> countByType();
}
