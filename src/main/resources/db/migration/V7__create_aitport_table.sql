CREATE TABLE airport
(
    airport_code char(3) NOT NULL, -- Код аэропорта
    airport_name text    NOT NULL, -- Название аэропорта
    city         text    NOT NULL, -- Город
    longitude    float   NOT NULL, -- Координаты аэропорта: долгота
    latitude     float   NOT NULL, -- Координаты аэропорта: широта
    timezone     text    NOT NULL, -- Часовой пояс аэропорта
    PRIMARY KEY (airport_code)
);