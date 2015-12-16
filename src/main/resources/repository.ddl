CREATE SEQUENCE seq_Category;
CREATE SEQUENCE seq_RuleOperand;
CREATE SEQUENCE seq_Value;
CREATE SEQUENCE seq_EntryKeyValue;
CREATE SEQUENCE seq_BundleKeyEntry;
CREATE SEQUENCE seq_Bundle;
CREATE SEQUENCE seq_Rule;
CREATE TABLE Category (
  CategoryID number(10) NOT NULL, 
  Sign       varchar2(255) NOT NULL, 
  PRIMARY KEY (CategoryID));
CREATE TABLE RuleOperand (
  RuleOperandID number(10) NOT NULL, 
  Name          varchar2(255) NOT NULL, 
  Sign          varchar2(255) NOT NULL, 
  PRIMARY KEY (RuleOperandID));
CREATE TABLE Value (
  ValueID      number(10) NOT NULL, 
  String_Value varchar2(255), 
  Int_Value    number(100), 
  Float_Value  varchar2(255), 
  Description  varchar2(255), 
  PRIMARY KEY (ValueID));
CREATE TABLE EntryKeyValue (
  EntryValueID number(10) NOT NULL, 
  ValueID      number(10) NOT NULL, 
  EntryID      number(10) NOT NULL, 
  PRIMARY KEY (EntryValueID));
CREATE TABLE BundleKeyEntry (
  EntryID  number(10) NOT NULL, 
  BundleID number(10) NOT NULL, 
  "Key"    varchar2(255) NOT NULL, 
  PRIMARY KEY (EntryID));
CREATE TABLE Bundle (
  BundleID number(10) NOT NULL, 
  PRIMARY KEY (BundleID));
CREATE TABLE "Rule" (
  RuleID      number(10) NOT NULL, 
  CategoryID  number(10) NOT NULL, 
  RuleOperand number(10), 
  BundleID    number(10), 
  Code        varchar2(255) NOT NULL, 
  Name        varchar2(255) NOT NULL, 
  Description varchar2(255) NOT NULL, 
  PRIMARY KEY (RuleID));
CREATE UNIQUE INDEX Category_CategoryID 
  ON Category (CategoryID);
CREATE UNIQUE INDEX RuleOperand_RuleOperandID 
  ON RuleOperand (RuleOperandID);
CREATE UNIQUE INDEX Value_ValueID 
  ON Value (ValueID);
CREATE UNIQUE INDEX EntryKeyValue_EntryValueID 
  ON EntryKeyValue (EntryValueID);
CREATE UNIQUE INDEX BundleKeyEntry_EntryID 
  ON BundleKeyEntry (EntryID);
CREATE UNIQUE INDEX Bundle_BundleID 
  ON Bundle (BundleID);
CREATE UNIQUE INDEX Rule_RuleID 
  ON "Rule" (RuleID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule511376 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);
ALTER TABLE BundleKeyEntry ADD CONSTRAINT FKBundleKeyE768418 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule979618 FOREIGN KEY (RuleOperand) REFERENCES RuleOperand (RuleOperandID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule230584 FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID);
ALTER TABLE EntryKeyValue ADD CONSTRAINT FKEntryKeyVa860260 FOREIGN KEY (EntryID) REFERENCES BundleKeyEntry (EntryID);
ALTER TABLE EntryKeyValue ADD CONSTRAINT FKEntryKeyVa198676 FOREIGN KEY (ValueID) REFERENCES Value (ValueID);
ALTER TABLE EntryKeyValue ADD CONSTRAINT FKEntryKeyVa198677 FOREIGN KEY (ValueID) REFERENCES Value (ValueID);
ALTER TABLE EntryKeyValue ADD CONSTRAINT FKEntryKeyVa860261 FOREIGN KEY (EntryID) REFERENCES BundleKeyEntry (EntryID);
ALTER TABLE BundleKeyEntry ADD CONSTRAINT FKBundleKeyE768419 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule230585 FOREIGN KEY (CategoryID) REFERENCES Category (CategoryID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule979619 FOREIGN KEY (RuleOperand) REFERENCES RuleOperand (RuleOperandID);
ALTER TABLE "Rule" ADD CONSTRAINT FKRule511377 FOREIGN KEY (BundleID) REFERENCES Bundle (BundleID);

