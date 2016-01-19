CREATE OR REPLACE TRIGGER BRG_{code}_{attribute_table}_TRIGGER
BEFORE DELETE OR INSERT OR UPDATE
ON {modify_table}
FOR EACH ROW
DECLARE
  L_OPER        VARCHAR2(3);
  L_ERROR_STACK VARCHAR2(4000);
BEGIN
  IF INSERTING
  THEN
    L_OPER := ''INS'';
  ELSIF UPDATING
    THEN
      L_OPER := ''UPD'';
  ELSIF DELETING
    THEN
      L_OPER := ''DEL'';
  END IF;
  DECLARE
    L_PASSED BOOLEAN := TRUE;
  BEGIN
    IF :NEW.{modify_column} = {value} AND L_OPER = ''UPD''
    THEN
    L_PASSED := :NEW.{modify_column} <= :OLD.{modify_column};
    IF NOT L_PASSED
    THEN
      L_ERROR_STACK := L_ERROR_STACK || {error}
    END IF;
  END IF;
END;