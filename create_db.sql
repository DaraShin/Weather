-- scripts to create Oracle db

CREATE TABLE weather (
    record_time DATE, 
    temperature_c NUMBER,
    wind_mph NUMBER,
    pressure_mb NUMBER,
    humidity INTEGER,
    condition VARCHAR2(50),
    latitude NUMBER,
    longitude NUMBER
);

ALTER TABLE weather 
ADD CONSTRAINT weather_pk PRIMARY KEY (record_time);