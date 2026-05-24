package tandraym.edu.rpg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tandraym.edu.rpg.dto.*;
import tandraym.edu.rpg.service.ItemService;

import java.util.UUID;

/**
 * REST controller for the Item Library.
 *
 * <pre>
 * GET    /api/cosmere/items           — paginated list + counts
 * GET    /api/cosmere/items/{id}      — single item (for edit modal)
 * POST   /api/cosmere/items           — create item
 * PUT    /api/cosmere/items/{id}      — update item
 * DELETE /api/cosmere/items/{id}      — delete item
 * </pre>
 */
@RestController
@RequestMapping("/api/cosmere/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // ── GET /api/cosmere/items ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<PagedItemsResponse> getAll(
            @RequestParam(required = false)              String type,
            @RequestParam(required = false)              String search,
            @RequestParam(defaultValue = "0")            int    page,
            @RequestParam(defaultValue = "20")           int    size
    ) {
        return ResponseEntity.ok(itemService.findAll(type, search, page, size));
    }

    // ── GET /api/cosmere/items/{id} ────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    // ── POST /api/cosmere/items ────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ItemDto> create(@RequestBody CreateItemRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(itemService.create(request));
    }

    // ── PUT /api/cosmere/items/{id} ────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> update(
            @PathVariable UUID id,
            @RequestBody UpdateItemRequest request) {
        return ResponseEntity.ok(itemService.update(id, request));
    }

    // ── DELETE /api/cosmere/items/{id} ─────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
