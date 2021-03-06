-- TABLES

CREATE TABLE CATEGORY (
  CATEGORYID NUMBER(10)    NOT NULL,
  NAME       VARCHAR2(255) NOT NULL,
  PRIMARY KEY (CATEGORYID)
);
CREATE TABLE BUSINESSRULE (
  BUSINESSRULEID NUMBER(10)    NOT NULL,
  CATEGORYID     NUMBER(10)    NOT NULL,
  RULEOPERANDID  NUMBER(10),
  BUNDLEID       NUMBER(10),
  CODE           VARCHAR2(255) NOT NULL,
  RULENAME       VARCHAR2(255) NOT NULL,
  DESCRIPTION    VARCHAR2(255) NOT NULL,
  CLASSNAME      VARCHAR2(255) NOT NULL,
  ACTIVE         NUMBER(10)    NOT NULL,
  ERRORMESSAGE   VARCHAR2(255),
  PRIMARY KEY (BUSINESSRULEID)
);
CREATE TABLE RULEOPERAND (
  RULEOPERANDID NUMBER(10)    NOT NULL,
  NAME          VARCHAR2(255) NOT NULL,
  SIGN          VARCHAR2(255) NOT NULL,
  PRIMARY KEY (RULEOPERANDID)
);
CREATE TABLE BUNDLE (
  BUNDLEID NUMBER(10) NOT NULL,
  PRIMARY KEY (BUNDLEID)
);
CREATE TABLE BUNDLEKEYENTRY (
  ENTRYID  NUMBER(10)    NOT NULL,
  BUNDLEID NUMBER(10)    NOT NULL,
  KEY      VARCHAR2(255) NOT NULL,
  PRIMARY KEY (ENTRYID)
);
CREATE TABLE ENTRYKEYVALUE (
  ENTRYVALUEID NUMBER(10) NOT NULL,
  ENTRYID      NUMBER(10) NOT NULL,
  STRING_VALUE VARCHAR2(255),
  INT_VALUE    NUMBER(10),
  FLOAT_VALUE  VARCHAR2(255),
  DESCRIPTION  VARCHAR2(255),
  PRIMARY KEY (ENTRYVALUEID)
);

-- CONSTRAINTS

ALTER TABLE BUSINESSRULE ADD CONSTRAINT FK1_BUSINESSRU FOREIGN KEY (CATEGORYID) REFERENCES CATEGORY (CATEGORYID);
ALTER TABLE BUSINESSRULE ADD CONSTRAINT FK2_BUSINESSRU FOREIGN KEY (RULEOPERANDID) REFERENCES RULEOPERAND (RULEOPERANDID);
ALTER TABLE BUSINESSRULE ADD CONSTRAINT FK3_BUSINESSRU FOREIGN KEY (BUNDLEID) REFERENCES BUNDLE (BUNDLEID);
ALTER TABLE BUNDLEKEYENTRY ADD CONSTRAINT FK1_BUNDLEKEYE FOREIGN KEY (BUNDLEID) REFERENCES BUNDLE (BUNDLEID);
ALTER TABLE ENTRYKEYVALUE ADD CONSTRAINT FK1_ENTRYKEYVA FOREIGN KEY (ENTRYID) REFERENCES BUNDLEKEYENTRY (ENTRYID);

-- SEQUENCES

CREATE SEQUENCE CAT_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;
CREATE SEQUENCE BR_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;
CREATE SEQUENCE OPE_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;
CREATE SEQUENCE BUN_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;
CREATE SEQUENCE BKE_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;
CREATE SEQUENCE EKV_SEQ_PK MINVALUE 0 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 190 NOCACHE ORDER NOCYCLE NOPARTITION;

-- TRIGGERSS

CREATE OR REPLACE TRIGGER CAT_TRG_PK
BEFORE INSERT ON CATEGORY
FOR EACH ROW
WHEN (NEW.CATEGORYID IS NULL)
  BEGIN
    :NEW.CATEGORYID := CAT_SEQ_PK.NEXTVAL;
  END;

CREATE OR REPLACE TRIGGER BR_TRG_PK
BEFORE INSERT ON BUSINESSRULE
FOR EACH ROW
WHEN (NEW.BUSINESSRULEID IS NULL)
  BEGIN
    :NEW.BUSINESSRULEID := BR_SEQ_PK.NEXTVAL;
  END;

CREATE OR REPLACE TRIGGER OPE_TRG_PK
BEFORE INSERT ON RULEOPERAND
FOR EACH ROW
WHEN (NEW.RULEOPERANDID IS NULL)
  BEGIN
    :NEW.RULEOPERANDID := OPE_SEQ_PK.NEXTVAL;
  END;

CREATE OR REPLACE TRIGGER BUN_TRG_PK
BEFORE INSERT ON BUNDLE
FOR EACH ROW
WHEN (NEW.BUNDLEID IS NULL)
  BEGIN
    :NEW.BUNDLEID := BUN_SEQ_PK.NEXTVAL;
  END;

CREATE OR REPLACE TRIGGER BKE_TRG_PK
BEFORE INSERT ON BUNDLEKEYENTRY
FOR EACH ROW
WHEN (NEW.ENTRYID IS NULL)
  BEGIN
    :NEW.ENTRYID := BKE_SEQ_PK.NEXTVAL;
  END;

CREATE OR REPLACE TRIGGER EKV_TRG_PK
BEFORE INSERT ON ENTRYKEYVALUE
FOR EACH ROW
WHEN (NEW.ENTRYVALUEID IS NULL)
  BEGIN
    :NEW.ENTRYVALUEID := EKV_SEQ_PK.NEXTVAL;
  END;