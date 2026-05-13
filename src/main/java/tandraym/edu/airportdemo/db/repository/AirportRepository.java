package tandraym.edu.airportdemo.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tandraym.edu.airportdemo.db.model.Airport;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirportRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<Airport> AIRPORT_ROW_MAPPER = (rs, rowNum) -> new Airport(
            rs.getString("airport_code"),
            rs.getString("airport_name"),
            rs.getString("city"),
            rs.getDouble("longitude"),
            rs.getDouble("latitude"),
            rs.getString("timezone")
    );

    public List<Airport> findAll() {
        String sql = """
                SELECT airport_code, airport_name, city, longitude, latitude, timezone
                FROM bookings.airport
                ORDER BY airport_code
                """;

        return jdbcTemplate.query(sql, AIRPORT_ROW_MAPPER);
    }

    public Optional<Airport> findByCode(String airportCode) {
        String sql = """
                SELECT airport_code, airport_name, city, longitude, latitude, timezone
                FROM bookings.airport
                WHERE airport_code = :airportCode
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("airportCode", airportCode);

        return jdbcTemplate.query(sql, params, AIRPORT_ROW_MAPPER).stream().findFirst();
    }

    public List<Airport> findByCity(String city) {
        String sql = """
                SELECT airport_code, airport_name, city, longitude, latitude, timezone
                FROM bookings.airport
                WHERE city = :city
                ORDER BY airport_code
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("city", city);

        return jdbcTemplate.query(sql, params, AIRPORT_ROW_MAPPER);
    }
}
