CREATE TABLE IF NOT EXISTS bookings.aircraft
(
    aircraft_code char(3) NOT NULL,
    model         text    NOT NULL,
    range         integer NOT NULL,
    CHECK (range > 0),
    PRIMARY KEY (aircraft_code)
);
