CREATE TABLE polygon (
    id SERIAL PRIMARY KEY,
    coordinateXOne FLOAT NOT NULL,
    coordinateYOne FLOAT NOT NULL,
    coordinateXTwo FLOAT NOT NULL,
    coordinateYTwo FLOAT NOT NULL,
    coordinateXThree FLOAT NOT NULL,
    coordinateYThree FLOAT NOT NULL,
    coordinateXFour FLOAT NOT NULL,
    coordinateYFour FLOAT NOT NULL,
    partnerId TEXT NOT NULL,
    FOREIGN KEY (partnerId) REFERENCES partner (id)
);