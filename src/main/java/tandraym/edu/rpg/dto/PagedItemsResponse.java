package tandraym.edu.rpg.dto;

import java.util.List;
import java.util.Map;

/**
 * Paginated response for GET /api/cosmere/items.
 *
 * <p>{@code counts} maps lowercase type name → item count for <em>all</em> items
 * in the library (ignoring the current page/filter) so the sidebar and stat-chips
 * always show the full totals.</p>
 *
 * <pre>
 * {
 *   "content": [ … ],
 *   "totalElements": 42,
 *   "totalPages": 3,
 *   "page": 0,
 *   "size": 20,
 *   "counts": { "weapon": 7, "armor": 4, "fabrial": 3, … }
 * }
 * </pre>
 */
public record PagedItemsResponse(
        List<ItemDto>       content,
        long                totalElements,
        int                 totalPages,
        int                 page,
        int                 size,
        Map<String, Long>   counts
) {}
