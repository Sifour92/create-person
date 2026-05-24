package tandraym.edu.rpg.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tandraym.edu.rpg.domain.Item;
import tandraym.edu.rpg.domain.ItemType;
import tandraym.edu.rpg.dto.*;
import tandraym.edu.rpg.repository.ItemRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    // ── Read ─────────────────────────────────────────────────────────────────

    /**
     * Returns a paginated, optionally filtered list of items together with
     * global type-counts (used by sidebar + stat-chips on items.html).
     *
     * @param type   lowercase type name; {@code null} = all types
     * @param search substring to match against name / descriptionShort;
     *               {@code null} or blank = no text filter
     * @param page   0-based page index
     * @param size   page size (max 100)
     */
    public PagedItemsResponse findAll(String type, String search, int page, int size) {
        ItemType itemType   = parseType(type);
        String   searchTerm = (search == null || search.isBlank()) ? null : search.trim();

        Pageable pageable = PageRequest.of(
                Math.max(0, page),
                Math.min(Math.max(1, size), 100),
                Sort.by("name")
        );

        Page<Item> resultPage = fetchPage(itemType, searchTerm, pageable);

        return new PagedItemsResponse(
                resultPage.getContent().stream().map(this::toDto).toList(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages(),
                page,
                size,
                buildCounts()
        );
    }

    public ItemDto findById(UUID id) {
        return itemRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Item not found: " + id));
    }

    // ── Write ────────────────────────────────────────────────────────────────

    @Transactional
    public ItemDto create(CreateItemRequest req) {
        Item item = Item.builder()
                .name(req.name())
                .type(parseTypeRequired(req.type()))
                .itemKey(req.itemKey())
                .img(req.img())
                .descriptionValue(req.descriptionValue())
                .descriptionShort(req.descriptionShort())
                .systemData(req.systemData())
                .build();

        return toDto(itemRepository.save(item));
    }

    @Transactional
    public ItemDto update(UUID id, UpdateItemRequest req) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found: " + id));

        item.setName(req.name());
        item.setType(parseTypeRequired(req.type()));
        item.setItemKey(req.itemKey());
        item.setImg(req.img());
        item.setDescriptionValue(req.descriptionValue());
        item.setDescriptionShort(req.descriptionShort());
        item.setSystemData(req.systemData());

        return toDto(itemRepository.save(item));
    }

    @Transactional
    public void delete(UUID id) {
        if (!itemRepository.existsById(id)) {
            throw new NoSuchElementException("Item not found: " + id);
        }
        itemRepository.deleteById(id);
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /**
     * Picks the correct repository method to avoid passing NULL parameters to
     * JPQL LOWER() — Hibernate 6 sends null as bytea which PostgreSQL rejects.
     */
    private Page<Item> fetchPage(ItemType type, String search, Pageable pageable) {
        boolean hasType   = type   != null;
        boolean hasSearch = search != null;

        if (hasType && hasSearch)   return itemRepository.findByTypeAndSearch(type, search, pageable);
        if (hasType)                return itemRepository.findByType(type, pageable);
        if (hasSearch)              return itemRepository.findBySearch(search, pageable);
        return                             itemRepository.findAll(pageable);
    }

    private Map<String, Long> buildCounts() {
        return itemRepository.countByType().stream()
                .collect(Collectors.toMap(
                        row -> ((ItemType) row[0]).name().toLowerCase(),
                        row -> (Long) row[1]
                ));
    }

    private ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getType().name().toLowerCase(),
                item.getItemKey(),
                item.getImg(),
                item.getDescriptionShort(),
                item.getSystemData()
        );
    }

    /** Parses nullable/optional type string — returns null if blank. */
    private ItemType parseType(String type) {
        if (type == null || type.isBlank()) return null;
        return parseTypeRequired(type);
    }

    /** Parses required type string — throws on null/blank/unknown. */
    private ItemType parseTypeRequired(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Item type must not be blank");
        }
        try {
            return ItemType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unknown item type: '%s'. Valid values: %s"
                            .formatted(type, Arrays.toString(ItemType.values()))
            );
        }
    }
}
