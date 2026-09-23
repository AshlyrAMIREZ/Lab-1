/*
Created: 7/19/2017
Modified: 9/22/2026
Model: UnaPlanilla
Database: Oracle 11g Release 2
*/




-- Create sequences section -------------------------------------------------

CREATE SEQUENCE plam_tipmovplanilla_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

CREATE SEQUENCE plam_tipomovimientos_seq01
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 MINVALUE 0
 NOCACHE
;

-- Create tables section -------------------------------------------------

-- Table plam_tipmovplanilla

CREATE TABLE plam_tipmovplanilla(
  tpm_id Number NOT NULL,
  tpm_tipomonto Varchar2(1 ) NOT NULL,
  tpm_monto Number NOT NULL,
  tpm_tipoapldedporc Varchar2(1 ) NOT NULL,
  tpm_version Number NOT NULL,
  tpla_id Number NOT NULL,
  tmov_id Number
)
;

-- Create indexes for table plam_tipmovplanilla

CREATE INDEX plam_tipmovplanilla_ind01 ON plam_tipmovplanilla (tpla_id)
;

-- Add keys for table plam_tipmovplanilla

ALTER TABLE plam_tipmovplanilla ADD CONSTRAINT PK_plam_tipmovplanilla PRIMARY KEY (tpm_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN plam_tipmovplanilla.tpm_id IS 'id del tipo de movimiento por planilla'
;
COMMENT ON COLUMN plam_tipmovplanilla.tpm_tipomonto IS 'Indica el tipo de monto (A: Absoluto, P: Porcentual)'
;
COMMENT ON COLUMN plam_tipmovplanilla.tpm_monto IS 'monto del movimiento'
;
COMMENT ON COLUMN plam_tipmovplanilla.tpm_tipoapldedporc IS 'Tipo de aplicacion de deducciones porcentuales segun ingresos(T:Todos, G:Gravables, R:Renta)'
;
COMMENT ON COLUMN plam_tipmovplanilla.tpm_version IS 'Version del registro'
;

-- Table plam_tipomovimientos

CREATE TABLE plam_tipomovimientos(
  tmov_id Number NOT NULL,
  tmov_tipo Varchar2(1 ) NOT NULL,
  tmov_descripcion Varchar2(40 ) NOT NULL,
  tmov_gravable Varchar2(1 ) NOT NULL,
  tmov_imprenta Varchar2(1 ) NOT NULL,
  tmov_tipomonto Varchar2(1 ) NOT NULL,
  tmov_monto Number NOT NULL,
  tmov_tipoapldedporc Varchar2(1 ) NOT NULL,
  tmov_estado Varchar2(1 ) NOT NULL,
  tmov_version Number NOT NULL
)
;

-- Add keys for table plam_tipomovimientos

ALTER TABLE plam_tipomovimientos ADD CONSTRAINT PK_plam_tipomovimientos PRIMARY KEY (tmov_id)
;

-- Table and Columns comments section

COMMENT ON COLUMN plam_tipomovimientos.tmov_id IS 'Id del tipo de movimiento'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_tipo IS 'Tipo de movimiento (I:Ingreso, D:Deduccion)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_descripcion IS 'Descripcion del tipo de movimiento'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_gravable IS 'Cuando es ingreso indica si se toma en cuenta para deducciones de impuestos(S: Si, N:No)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_imprenta IS 'Cuando es ingreso se indica si se toma en cuenta para impuestos de renta(S:Si, N:No)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_tipomonto IS 'Indica el tipo de monto(A:Absoluto, P:Porcentual)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_monto IS 'Monto del movimiento'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_tipoapldedporc IS 'Tipo de aplicacion de deducciones porcentuales segun ingresos(T:Todos, G:Gravables,R:Renta)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_estado IS 'Estado del tipo de movimiento (A:Activo, I:Inactivo)'
;
COMMENT ON COLUMN plam_tipomovimientos.tmov_version IS 'Version del registro'
;

-- Trigger for sequence plam_tipmovplanilla_seq01 for column tpm_id in table plam_tipmovplanilla ---------
CREATE OR REPLACE TRIGGER plam_tipmovplanilla_tgr01 BEFORE INSERT
ON plam_tipmovplanilla FOR EACH ROW
BEGIN
  IF :new.tpm_id IS NULL OR :new.tpm_id <= 0 THEN
     :new.tpm_id := plam_tipmovplanilla_seq01.nextval;
  END IF;
END;
/
CREATE OR REPLACE TRIGGER plam_tipmovplanilla_tgr02 AFTER UPDATE OF tpm_id
ON plam_tipmovplanilla FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column tpm_id in table plam_tipmovplanilla as it uses sequence.');
END;
/

-- Trigger for sequence plam_tipomovimientos_seq01 for column tmov_id in table plam_tipomovimientos ---------
CREATE OR REPLACE TRIGGER plam_tipomovimientos_plam_01 BEFORE INSERT
ON plam_tipomovimientos FOR EACH ROW
BEGIN
  IF :new.tmov_id IS NULL OR :new.tmov_id <= 0 THEN
     :new.tmov_id := plam_tipomovimientos_seq01.nextval
  END IF;
END;
/
CREATE OR REPLACE TRIGGER plam_tipomovimientos_plam_02 AFTER UPDATE OF tmov_id
ON plam_tipomovimientos FOR EACH ROW
BEGIN
  RAISE_APPLICATION_ERROR(-20010,'Cannot update column tmov_id in table plam_tipomovimientos as it uses sequence.');
END;
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE plam_tipmovplanilla ADD CONSTRAINT plam_tipmovplanilla_fk02 FOREIGN KEY (tpla_id) REFERENCES plam_tipoplanillas (tpla_id)
;



ALTER TABLE plam_tipmovplanilla ADD CONSTRAINT plam_tipmovplanilla_fx01 FOREIGN KEY (tmov_id) REFERENCES plam_tipomovimientos (tmov_id)
;







