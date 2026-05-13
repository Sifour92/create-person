package tandraym.edu.airportdemo.cosmere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tandraym.edu.airportdemo.cosmere.dto.CreateCharacterRequest;
import tandraym.edu.airportdemo.cosmere.dto.PlayerCharacterDto;
import tandraym.edu.airportdemo.cosmere.dto.UpdateAttributesRequest;
import tandraym.edu.airportdemo.cosmere.service.CharacterService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cosmere/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    // GET /api/cosmere/characters
    @GetMapping
    public List<PlayerCharacterDto> getAll() {
        return characterService.findAll();
    }

    // GET /api/cosmere/characters/{id}
    @GetMapping("/{id}")
    public PlayerCharacterDto getById(@PathVariable UUID id) {
        return characterService.findById(id);
    }

    // POST /api/cosmere/characters
    // Body: { "name": "Аосей", "tier": 1, "size": "medium" }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerCharacterDto create(@RequestBody CreateCharacterRequest request) {
        return characterService.create(request);
    }

    // PATCH /api/cosmere/characters/{id}/attributes
    // Body: { "strength": 3, "speed": 2 }  — только те поля, которые меняем
    @PatchMapping("/{id}/attributes")
    public PlayerCharacterDto updateAttributes(
        @PathVariable UUID id,
        @RequestBody UpdateAttributesRequest request
    ) {
        return characterService.updateAttributes(id, request);
    }

    // PATCH /api/cosmere/characters/{id}/skills/{skillKey}?rank=2
    // Пример: PATCH /api/cosmere/characters/{id}/skills/lwp?rank=2
    @PatchMapping("/{id}/skills/{skillKey}")
    public PlayerCharacterDto updateSkill(
        @PathVariable UUID   id,
        @PathVariable String skillKey,
        @RequestParam int    rank
    ) {
        return characterService.updateSkill(id, skillKey, rank);
    }

    // POST /api/cosmere/characters/{id}/items/{itemId}
    // Добавить item (ancestry/culture/path/action) к персонажу
    @PostMapping("/{id}/items/{itemId}")
    public PlayerCharacterDto addItem(
        @PathVariable UUID id,
        @PathVariable UUID itemId
    ) {
        return characterService.addItem(id, itemId);
    }

    // DELETE /api/cosmere/characters/{id}/items/{itemId}
    @DeleteMapping("/{id}/items/{itemId}")
    public PlayerCharacterDto removeItem(
        @PathVariable UUID id,
        @PathVariable UUID itemId
    ) {
        return characterService.removeItem(id, itemId);
    }

    // DELETE /api/cosmere/characters/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        characterService.delete(id);
    }

    @ExceptionHandler(java.util.NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFound(java.util.NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
