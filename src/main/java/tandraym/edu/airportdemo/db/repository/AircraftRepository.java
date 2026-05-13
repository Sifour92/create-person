package tandraym.edu.airportdemo.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tandraym.edu.airportdemo.db.model.Aircraft;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AircraftRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<Aircraft> AIRCRAFT_ROW_MAPPER = (rs, rowNum) -> new Aircraft(
            rs.getString("aircraft_code"),
            rs.getString("model"),
            rs.getInt("range")
    );

    public List<Aircraft> findAllByMinRangeOrderByRangeDesc(int minRange) {
        String sql = """
                SELECT aircraft_code, model, range
                FROM bookings.aircraft
                WHERE range >= :minRange
                ORDER BY range DESC, aircraft_code ASC
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("minRange", minRange);

        return jdbcTemplate.query(sql, params, AIRCRAFT_ROW_MAPPER);
    }
}
