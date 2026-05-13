package tandraym.edu.airportdemo.cosmere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tandraym.edu.airportdemo.cosmere.domain.PlayerCharacter;
import tandraym.edu.airportdemo.cosmere.dto.CreateCharacterRequest;
import tandraym.edu.airportdemo.cosmere.dto.PlayerCharacterDto;
import tandraym.edu.airportdemo.cosmere.dto.UpdateAttributesRequest;
import tandraym.edu.airportdemo.cosmere.repository.CharacterRepository;
import tandraym.edu.airportdemo.cosmere.repository.ItemRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final ItemRepository      itemRepository;
    private final CharacterMapper     mapper;

    public List<PlayerCharacterDto> findAll() {
        return characterRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public PlayerCharacterDto findById(UUID id) {
        return characterRepository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Character not found: " + id));
    }

    @Transactional
    public PlayerCharacterDto create(CreateCharacterRequest request) {
        var character = new PlayerCharacter();
        character.setName(request.name());
        character.setTier(request.tier());
        character.setSize(request.size());
        character.setImg(request.img());
        return mapper.toDto(characterRepository.save(character));
    }

    @Transactional
    public PlayerCharacterDto updateAttributes(UUID id, UpdateAttributesRequest request) {
        var character = getOrThrow(id);
        var attrs = character.getAttributes();

        if (request.strength()  != null) attrs.getStrength().setValue(request.strength());
        if (request.speed()     != null) attrs.getSpeed().setValue(request.speed());
        if (request.intellect() != null) attrs.getIntellect().setValue(request.intellect());
        if (request.willpower() != null) attrs.getWillpower().setValue(request.willpower());
        if (request.awareness() != null) attrs.getAwareness().setValue(request.awareness());
        if (request.presence()  != null) attrs.getPresence().setValue(request.presence());

        return mapper.toDto(characterRepository.save(character));
    }

    @Transactional
    public PlayerCharacterDto updateSkill(UUID characterId, String skillKey, int rank) {
        var character = getOrThrow(characterId);
        character.getSkills().stream()
            .filter(s -> s.getId().getSkillKey().equals(skillKey))
            .findFirst()
            .ifPresent(s -> s.setRank(rank));
        return mapper.toDto(characterRepository.save(character));
    }

    // Information Expert: Character знает свои items → он управляет добавлением
    @Transactional
    public PlayerCharacterDto addItem(UUID characterId, UUID itemId) {
        var character = getOrThrow(characterId);
        var item = itemRepository.findById(itemId)
            .orElseThrow(() -> new NoSuchElementException("Item not found: " + itemId));

        boolean alreadyAdded = character.getItems().stream()
            .anyMatch(i -> i.getId().equals(itemId));
        if (!alreadyAdded) {
            character.addItem(item);
            characterRepository.save(character);
        }
        return mapper.toDto(character);
    }

    @Transactional
    public PlayerCharacterDto removeItem(UUID characterId, UUID itemId) {
        var character = getOrThrow(characterId);
        character.removeItem(itemId);
        return mapper.toDto(characterRepository.save(character));
    }

    @Transactional
    public void delete(UUID id) {
        if (!characterRepository.existsById(id)) {
            throw new NoSuchElementException("Character not found: " + id);
        }
        characterRepository.deleteById(id);
    }

    private PlayerCharacter getOrThrow(UUID id) {
        return characterRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Character not found: " + id));
    }
}
