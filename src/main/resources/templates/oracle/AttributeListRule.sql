CREATE OR REPLACE TRIGGER BRG_{code}_{attribute_table}_TRG
BEFORE DELETE OR INSERT OR UPDATE
ON {attribute_table}
FOR EACH ROW
DECLARE
  L_PASSED BOOLEAN := FALSE;
  V_COLUMN_1    VARCHAR2(60) := :NEW.{attribute_column};
BEGIN
    IF '{operand}' = '=' THEN
      IF V_COLUMN_1 IN ({list_list}) THEN
        L_PASSED := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, {error});
      END IF;
    END IF;

    IF '{operand}' = '!=' THEN
      IF V_COLUMN_1 NOT IN ({list_list}) THEN
        L_PASSED := TRUE;
      ELSE
        RAISE_APPLICATION_ERROR(-20000, {error});
      END IF;
    END IF;

  IF NOT L_PASSED THEN
    RAISE_APPLICATION_ERROR(-20000, 'Error: Something went wrong!' );
    END IF;
  END;