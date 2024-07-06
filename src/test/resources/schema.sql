CREATE TABLE IF NOT EXISTS releases (
    id          UUID    PRIMARY KEY,
    version     TEXT    NOT NULL,
    description TEXT    NOT NULL
);
