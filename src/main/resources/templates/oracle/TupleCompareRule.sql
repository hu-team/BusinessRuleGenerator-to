CREATE OR REPLACE TRIGGER BRG_{code}_{attribute_table}_TRIGGER
BEFORE DELETE OR INSERT OR UPDATE
ON {tuple_table}
FOR EACH ROW
DECLARE
  L_OPER        VARCHAR2(3);
  L_ERROR_STACK VARCHAR2(4000);
BEGIN
  IF INSERTING
  THEN
    L_OPER := 'INS';
  ELSIF UPDATING
    THEN
      L_OPER := 'UPD';
  ELSIF DELETING
    THEN
      L_OPER := 'DEL';
  END IF;

  DECLARE
    L_PASSED BOOLEAN := TRUE;
  BEGIN
    IF L_OPER IN ('INS', 'UPD')
    THEN
      L_PASSED := :NEW.{tuple_column_1} {operand} :NEW.{tuple_column_2};
      IF NOT L_PASSED
      THEN
        L_ERROR_STACK := L_ERROR_STACK || {error};
      END IF;
    END IF;
  END;
END;