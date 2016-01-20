CREATE OR REPLACE TRIGGER BRG_{code}_{tuple_table}_TRG
BEFORE INSERT OR UPDATE
ON {tuple_table}
FOR EACH ROW
DECLARE
  L_PASSED      BOOLEAN := TRUE;
  V_COLUMN_1    VARCHAR2(60) := :NEW.{tuple_column_1};
  V_COLUMN_2    VARCHAR2(255) := :NEW.{tuple_column_2};
BEGIN
  IF (V_COLUMN_1 {operand} V_COLUMN_2) THEN
    L_PASSED := TRUE;
  ELSE
    RAISE_APPLICATION_ERROR(-20000, 'Error Raised: ' || {error});
  END IF;

END;
