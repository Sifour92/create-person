package tandraym.edu.airportdemo.cosmere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tandraym.edu.airportdemo.cosmere.domain.PlayerCharacter;

import java.util.List;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<PlayerCharacter, UUID> {
    List<PlayerCharacter> findByNameContainingIgnoreCase(String name);
}
