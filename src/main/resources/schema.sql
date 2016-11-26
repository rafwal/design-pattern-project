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


CREATE SEQUENCE RequestSequence;

CREATE TABLE Request (
  id INT8 PRIMARY KEY,
  threadgroupid INT8 NOT NULL REFERENCES ThreadGroup(id),
  url VARCHAR(2000) NOT NULL,
  method VARCHAR(6) NOT NULL,
  body TEXT
);

CREATE TABLE Header (
  requestid INT8 NOT NULL REFERENCES Request(id),
  key varchar(255) NOT NULL,
  value text NOT NULL,

  PRIMARY KEY (requestid, key)
);

COMMIT;