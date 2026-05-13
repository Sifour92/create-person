package tandraym.edu.airportdemo.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import tandraym.edu.airportdemo.db.projection.AircraftFareConditionsCount;
import tandraym.edu.airportdemo.db.projection.AircraftSeatCount;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final RowMapper<AircraftSeatCount> AIRCRAFT_SEAT_COUNT_ROW_MAPPER = (rs, rowNum) ->
            new AircraftSeatCount(
                    rs.getString("aircraft_code"),
                    rs.getLong("count")
            );

    private static final RowMapper<AircraftFareConditionsCount> AIRCRAFT_FARE_CONDITIONS_COUNT_ROW_MAPPER = (rs, rowNum) ->
            new AircraftFareConditionsCount(
                    rs.getString("aircraft_code"),
                    rs.getString("fare_conditions"),
                    rs.getLong("count")
            );

    public List<AircraftSeatCount> countSeatsByAircraftOrderByCount() {
        String sql = """
                SELECT aircraft_code, count(*) AS count
                FROM bookings.seats
                GROUP BY aircraft_code
                ORDER BY count
                """;

        return jdbcTemplate.query(sql, AIRCRAFT_SEAT_COUNT_ROW_MAPPER);
    }

    public List<AircraftFareConditionsCount> countSeatsByAircraftAndFareConditions() {
        String sql = """
                SELECT aircraft_code, fare_conditions, count(*) AS count
                FROM bookings.seats
                GROUP BY aircraft_code, fare_conditions
                ORDER BY aircraft_code, fare_conditions
                """;

        return jdbcTemplate.query(sql, AIRCRAFT_FARE_CONDITIONS_COUNT_ROW_MAPPER);
    }
}
