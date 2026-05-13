CREATE TABLE bookings.seat
(
    aircraft_code   char( 3 )     NOT NULL,
    seat_no         varchar( 4 )  NOT NULL,
    fare_conditions varchar( 10 ) NOT NULL,
    CHECK
        ( fare_conditions IN ( 'Economy', 'Comfort', 'Business' )
        ),
    PRIMARY KEY ( aircraft_code, seat_no ),
    FOREIGN KEY ( aircraft_code )
        REFERENCES aircraft (aircraft_code )
        ON DELETE CASCADE
);
