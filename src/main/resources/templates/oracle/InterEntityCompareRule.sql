CREATE OR REPLACE TRIGGER BRG_{code}_{interentity_table_1}_TRG
BEFORE DELETE OR INSERT OR UPDATE
ON {interentity_table_1}
FOR EACH ROW
DECLARE
  L_PASSED BOOLEAN := TRUE;

  V_TABLE_1    VARCHAR2(60) := {interentity_table_1};

  V_COLUMN_1    VARCHAR2(60) := :NEW.{interentity_column_1};

  V_COLUMN_2_VALUE varchar(60);

BEGIN
   SELECT {interentity_column_2}
   INTO V_COLUMN_2_VALUE
   FROM {interentity_table_2}
   WHERE MAX(ROWNUM);

  IF (V_COLUMN_1 {operand} V_COLUMN_2_VALUE) THEN
    L_PASSED := TRUE;
  ELSE
    RAISE_APPLICATION_ERROR(-20000, {error});
  END IF;
END;