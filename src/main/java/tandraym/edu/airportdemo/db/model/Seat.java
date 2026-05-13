package tandraym.edu.airportdemo.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import tandraym.edu.airportdemo.db.model.pk.SeatId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@IdClass(SeatId.class)
@Table(name = "seats", schema = "bookings")
public class Seat {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "aircraft_code", columnDefinition = "char(3)", length = 3, nullable = false)
    private String aircraftCode;

    @Id
    @Column(name = "seat_no", length = 4, nullable = false)
    private String seatNo;

    @Column(name = "fare_conditions", length = 10, nullable = false)
    private String fareConditions;
}
