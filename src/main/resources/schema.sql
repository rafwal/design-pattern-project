DROP TABLE IF EXISTS ThreadGroup;
DROP TABLE IF EXISTS Request;

DROP SEQUENCE IF EXISTS ThreadGroupSequence;
DROP SEQUENCE IF EXISTS RequestSequence;

BEGIN;

CREATE SEQUENCE ThreadGroupSequence;

CREATE TABLE ThreadGroup (
  id INT8 PRIMARY KEY,
  threadsCount INT8 NOT NULL,
  rampUpPeriod INT8 NOT NULL,
  loopCount INT8 NOT NULL,
  delay INT8
);


CREATE SEQUENCE TestSequence;

CREATE TABLE Test (
  id INT8 PRIMARY KEY,
  threadgroupid INT8 NOT NULL REFERENCES ThreadGroup(id),
  url VARCHAR(2000) NOT NULL,
  method VARCHAR(6) NOT NULL,
  body TEXT
);

CREATE TABLE Header (
  testid INT8 NOT NULL REFERENCES Test(id),
  key varchar(255) NOT NULL,
  value text NOT NULL,

  PRIMARY KEY (testid, key)
);

INSERT INTO ThreadGroup VALUES(nextval('ThreadGroupSequence'), 1, 1, 1, 0);
INSERT INTO Test VALUES(nextval('TestSequence'), 1, 'https://google.pl', 'GET', null);

COMMIT;