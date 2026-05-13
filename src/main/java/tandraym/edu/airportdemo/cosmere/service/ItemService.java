package tandraym.edu.airportdemo.cosmere.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tandraym.edu.airportdemo.cosmere.domain.Item;
import tandraym.edu.airportdemo.cosmere.domain.ItemType;
import tandraym.edu.airportdemo.cosmere.dto.CreateItemRequest;
import tandraym.edu.airportdemo.cosmere.dto.ItemDto;
import tandraym.edu.airportdemo.cosmere.repository.ItemRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final CharacterMapper mapper;

    public List<ItemDto> findAll(ItemType type) {
        var items = (type != null)
            ? itemRepository.findByType(type)
            : itemRepository.findAll();
        return items.stream().map(mapper::toItemDto).toList();
    }

    public ItemDto findById(UUID id) {
        return itemRepository.findById(id)
            .map(mapper::toItemDto)
            .orElseThrow(() -> new NoSuchElementException("Item not found: " + id));
    }

    @Transactional
    public ItemDto create(CreateItemRequest request) {
        var item = new Item();
        item.setName(request.name());
        item.setType(request.type());
        item.setItemKey(request.itemKey());
        item.setImg(request.img());
        item.setDescriptionValue(request.descriptionValue());
        item.setDescriptionShort(request.descriptionShort());
        item.setSystemData(request.systemData());
        return mapper.toItemDto(itemRepository.save(item));
    }

    @Transactional
    public void delete(UUID id) {
        if (!itemRepository.existsById(id)) {
            throw new NoSuchElementException("Item not found: " + id);
        }
        itemRepository.deleteById(id);
    }
}
