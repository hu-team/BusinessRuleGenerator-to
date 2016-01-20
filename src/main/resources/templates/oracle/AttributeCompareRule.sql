CREATE OR REPLACE TRIGGER BRG_{code}_{attribute_table}_TRG
BEFORE INSERT OR UPDATE
ON {attribute_table}
FOR EACH ROW
DECLARE
  L_PASSED       BOOLEAN := FALSE;
  V_COLUMN_1     VARCHAR2(60) := :NEW.{attribute_column};
  V_COMVALUE     VARCHAR2(255) := '{compare_with}';
  V_COMVALUE_NUM NUMBER(10);
BEGIN


  IF REGEXP_LIKE(V_COMVALUE, '^[[:digit:]]+$') THEN
  V_COMVALUE_NUM := TO_NUMBER(V_COMVALUE);

    IF (V_COLUMN_1 {operand} V_COMVALUE_NUM) THEN
      L_PASSED := TRUE;
    ELSE
      RAISE_APPLICATION_ERROR(-20000, 'Error Raised: ' || {error});
    END IF;
  END IF;
  IF NOT L_PASSED THEN
    IF (V_COLUMN_1 {operand} V_COMVALUE) THEN
      L_PASSED := TRUE;
    ELSE
      RAISE_APPLICATION_ERROR(-20000, 'Error Raised: ' || {error});
    END IF;
  END IF;
END;