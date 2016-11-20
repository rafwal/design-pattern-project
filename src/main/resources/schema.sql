DROP TABLE IF EXISTS person;

BEGIN;

CREATE TABLE person (
  personid INT8 PRIMARY KEY,
  fullname VARCHAR(30) NOT NULL,
  saldo    NUMERIC(7,2) NOT NULL
);

INSERT INTO person(personid, fullname, saldo) VALUES (1,'Rafal Wal', 345.21);

COMMIT;

