CREATE TABLE partner (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    tradingName TEXT NOT NULL,
    ownerName TEXT NOT NULL,
    document TEXT NOT NULL UNIQUE,
    coordinateX FLOAT NOT NULL,
    coordinateY FLOAT NOT NULL,
    coverageArea geometry(MultiPolygon)
);