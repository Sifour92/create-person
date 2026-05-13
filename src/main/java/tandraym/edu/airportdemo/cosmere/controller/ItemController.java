package tandraym.edu.airportdemo.cosmere.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tandraym.edu.airportdemo.cosmere.domain.ItemType;
import tandraym.edu.airportdemo.cosmere.dto.CreateItemRequest;
import tandraym.edu.airportdemo.cosmere.dto.ItemDto;
import tandraym.edu.airportdemo.cosmere.service.ItemService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cosmere/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // GET /api/cosmere/items
    // GET /api/cosmere/items?type=PATH   — фильтр по типу
    @GetMapping
    public List<ItemDto> getAll(@RequestParam(required = false) ItemType type) {
        return itemService.findAll(type);
    }

    // GET /api/cosmere/items/{id}
    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable UUID id) {
        return itemService.findById(id);
    }

    // POST /api/cosmere/items
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestBody CreateItemRequest request) {
        return itemService.create(request);
    }

    // DELETE /api/cosmere/items/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        itemService.delete(id);
    }

    @ExceptionHandler(java.util.NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFound(java.util.NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
