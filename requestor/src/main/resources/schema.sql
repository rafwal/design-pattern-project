DROP TABLE IF EXISTS ThreadGroup;
DROP TABLE IF EXISTS Request;

DROP SEQUENCE IF EXISTS ThreadGroupSequence;
DROP SEQUENCE IF EXISTS RequestSequence;

BEGIN;

CREATE SEQUENCE ThreadGroupSequence;

CREATE TABLE ThreadGroup (
  id BIGINT PRIMARY KEY,
  threadsCount INT8 NOT NULL,
  rampUpPeriod INT8 NOT NULL,
  loopCount INT8 NOT NULL,
  delay INT8
);


CREATE SEQUENCE TestDefinitionSequence;

CREATE TABLE TestDefinition (
  id BIGINT PRIMARY KEY,
  json TEXT,
  pluginName VARCHAR(30)
);

CREATE SEQUENCE TestExecutionSequence;

CREATE TABLE TestExecution (
  id BIGINT PRIMARY KEY,
  testDefinitionId BIGINT REFERENCES TestDefinition(id),
  threadGroupId BIGINT REFERENCES ThreadGroup(id),
  timeout INT8,
  startTime TIMESTAMP,
  endTime TIMESTAMP,
  state VARCHAR(15)
);

CREATE SEQUENCE SingleExecutionSequence;

CREATE TABLE SingleExecution (
  id BIGINT PRIMARY KEY,
  testExecutionId BIGINT REFERENCES TestExecution(id),
  startTime TIMESTAMP,
  endTime TIMESTAMP,
  output TEXT,
  threadNo INT8
);


INSERT INTO ThreadGroup VALUES(nextval('ThreadGroupSequence'), 10, 1, 5, 1000);
INSERT INTO TestDefinition VALUES(nextval('TestDefinitionSequence'), '{"url": "http://www.google.pl/","method": "GET","body": null,"headers": {"header": "value"}}', 'HTTP');

COMMIT;