CREATE TABLE Category (
  CategoryID number(10) NOT NULL, 
  Sign       varchar2(255) NOT NULL, 
  PRIMARY KEY (CategoryID));
CREATE TABLE BusinessRule (
  BusinessRuleID number(10) NOT NULL, 
  CategoryID     number(10) NOT NULL, 
  RuleOperandID  number(10), 
  BundleID       number(10), 
  Code           varchar2(255) NOT NULL, 
  Name           varchar2(255) NOT NULL, 
  Description    varchar2(255) NOT NULL, 
  PRIMARY KEY (BusinessRuleID));
CREATE TABLE RuleOperand (
  RuleOperandID number(10) NOT NULL, 
  Name          varchar2(255) NOT NULL, 
  Sign          varchar2(255) NOT NULL, 
  PRIMARY KEY (RuleOperandID));
CREATE TABLE Bundle (
  BundleID number(10) NOT NULL, 
  PRIMARY KEY (BundleID));
CREATE TABLE BundleKeyEntry (
  EntryID  number(10) NOT NULL, 
  BundleID number(10) NOT NULL, 
  Key    varchar2(255) NOT NULL, 
  PRIMARY KEY (EntryID));
CREATE TABLE EntryKeyValue (
  EntryValueID number(10) NOT NULL, 
  EntryID      number(10) NOT NULL, 
  String_Value varchar2(255), 
  Int_Value    number(10), 
  Float_Value  varchar2(255), 
  Description  varchar2(255), 
  PRIMARY KEY (EntryValueID));

CREATE SEQUENCE cat_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER cat_trg
  BEFORE INSERT ON Category
  FOR EACH ROW
BEGIN 
  SELECT cat_seq.NEXTVAL
    INTO   :new.CategoryID
    FROM   dual;  
END;

CREATE SEQUENCE br_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER br_trg
  BEFORE INSERT ON BusinessRule
  FOR EACH ROW
BEGIN 
  SELECT br_seq.NEXTVAL
    INTO   :new.BusinessRuleID
    FROM   dual;  
END;

CREATE SEQUENCE ope_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER ope_trg
  BEFORE INSERT ON RuleOperand
  FOR EACH ROW
BEGIN 
  SELECT ope_seq.NEXTVAL
    INTO   :new.RuleOperandID
    FROM   dual;  
END;

CREATE SEQUENCE bun_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER bun_trg
  BEFORE INSERT ON Bundle
  FOR EACH ROW
BEGIN 
  SELECT bun_seq.NEXTVAL
    INTO   :new.BundleID
    FROM   dual;  
END;

CREATE SEQUENCE bke_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER bke_trg
  BEFORE INSERT ON BundleKeyEntry
  FOR EACH ROW
BEGIN 
  SELECT bke_seq.NEXTVAL
    INTO   :new.EntryID
    FROM   dual;  
END;

CREATE SEQUENCE ekv_seq
  START WITH 100000
  INCREMENT BY 1
  CACHE 1;

CREATE OR REPLACE TRIGGER ekv_trg
  BEFORE INSERT ON EntryKeyValue
  FOR EACH ROW
BEGIN 
  SELECT ekv_seq.NEXTVAL
    INTO   :new.EntryValueID
    FROM   dual;  
END;


ALTER TABLE BusinessRule ADD CONSTRAINT FKBusinessRu704047 FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID);
ALTER TABLE BusinessRule ADD CONSTRAINT FKBusinessRu989116 FOREIGN KEY (RuleOperandID) REFERENCES RuleOperand (RuleOperandID);
ALTER TABLE BusinessRule ADD CONSTRAINT FKBusinessRu548335 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);
ALTER TABLE BundleKeyEntry ADD CONSTRAINT FKBundleKeyE768418 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);
ALTER TABLE EntryKeyValue ADD CONSTRAINT FKEntryKeyVa860260 FOREIGN KEY (EntryID) REFERENCES BundleKeyEntry (EntryID);
