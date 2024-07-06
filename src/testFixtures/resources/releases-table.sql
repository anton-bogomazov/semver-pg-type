CREATE TABLE IF NOT EXISTS releases (
    id          UUID    PRIMARY KEY,
    version     SEMVER  NOT NULL,
    description TEXT    NOT NULL
);
