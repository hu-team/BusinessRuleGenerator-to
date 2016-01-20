CREATE OR REPLACE TRIGGER BRG_{code}_{attribute_table}_TRG
BEFORE DELETE OR INSERT OR UPDATE
ON {attribute_table}
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
      IF '{operand}' = '=' THEN
        L_PASSED := :NEW.{attribute_column} >= {range_min} AND :NEW.{attribute_column} <= {range_max};
      ELSE
        L_PASSED := :NEW.{attribute_column} < {range_min} OR :NEW.{attribute_column} > {range_max};
      END IF;

      IF NOT L_PASSED
      THEN
        raise_application_error(-20000, {error});
      END IF;
    END IF;
  END;
END;