package tandraym.edu.airportdemo.cosmere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tandraym.edu.airportdemo.cosmere.domain.Item;
import tandraym.edu.airportdemo.cosmere.domain.ItemType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByType(ItemType type);
    Optional<Item> findByItemKey(String itemKey);
}
