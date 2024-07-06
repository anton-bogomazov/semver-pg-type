CREATE TYPE SEMVER AS (
    major int,
    minor int,
    patch int
);

CREATE OR REPLACE FUNCTION semver_less_than(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        IF a.major < b.major THEN
            RETURN TRUE;
        ELSIF a.major = b.major THEN
            IF a.minor < b.minor THEN
                RETURN TRUE;
            ELSIF a.minor = b.minor THEN
                RETURN a.patch < b.patch;
            ELSE
                RETURN FALSE;
            END IF;
        ELSE
            RETURN FALSE;
        END IF;
    END;
    '
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION semver_less_than_or_equal_to(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        RETURN semver_less_than(a, b) OR (a.major = b.major AND a.minor = b.minor AND a.patch = b.patch);
    END;
    '
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION semver_equal(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        RETURN a.major = b.major AND a.minor = b.minor AND a.patch = b.patch;
    END;
    '
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION semver_greater_than_or_equal_to(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        RETURN NOT semver_less_than(a, b);
    END;
    '
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION semver_greater_than(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        RETURN NOT semver_less_than_or_equal_to(a, b);
    END;
    '
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION semver_not_equal(a semver, b semver) RETURNS BOOLEAN AS
    '
    BEGIN
        RETURN NOT semver_equal(a, b);
    END;
    '
LANGUAGE PLPGSQL;

CREATE OPERATOR < (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_less_than);
CREATE OPERATOR <= (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_less_than_or_equal_to);
CREATE OPERATOR = (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_equal);
CREATE OPERATOR >= (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_greater_than_or_equal_to);
CREATE OPERATOR > (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_greater_than);
CREATE OPERATOR <> (LEFTARG = semver, RIGHTARG = semver, PROCEDURE = semver_not_equal);
