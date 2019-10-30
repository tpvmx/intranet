/*
ALTER TABLE
    "intranetaoc"."public"."tb_noticia" ADD COLUMN color_titulo CHARACTER VARYING;
ALTER TABLE
    "intranetaoc"."public"."tb_noticia" ADD COLUMN color_subtitulo CHARACTER VARYING;
EJECUTADOS 11-06-2018   
    
ALTER TABLE
    "intranetaoc"."public"."tb_empleado" ADD COLUMN tarjeta CHARACTER VARYING;
ALTER TABLE
    "intranetaoc"."public"."tb_empleado" ADD COLUMN banco CHARACTER VARYING;
ALTER TABLE
    "intranetaoc"."public"."tb_empleado" ADD COLUMN clabe NUMERIC(20);
ALTER TABLE
    "intranetaoc"."public"."tb_empleado" ADD COLUMN cuenta NUMERIC(20);
ALTER TABLE
    "intranetaoc"."public"."tb_gastos_viaje" DROP COLUMN "tarjeta";
ALTER TABLE
    "intranetaoc"."public"."tb_gastos_viaje" DROP COLUMN "banco";
ALTER TABLE
    "intranetaoc"."public"."tb_gastos_viaje" DROP COLUMN "clabe";
ALTER TABLE
    "intranetaoc"."public"."tb_gastos_viaje" DROP COLUMN "cuenta";
ALTER TABLE
    "intranetaoc"."public"."tb_gastos_viaje" ADD COLUMN folio INTEGER;
ALTER TABLE
    "intranetaoc"."public"."tb_vacacion" ADD COLUMN folio INTEGER;
    
CREATE TABLE
intranetaoc.public.tb_folio
(
    referencia CHARACTER VARYING,
    folio INTEGER,
    anio INTEGER,
    CONSTRAINT pk_folios PRIMARY KEY (referencia)
);

*/ 

CREATE TABLE
    tb_juntas
    (
        id_junta INTEGER NOT NULL,
        descripcion CHARACTER VARYING,
        fechajuntainicio TIMESTAMP(6) WITH TIME ZONE,
        estatus SMALLINT,
        feccrea TIMESTAMP(6) WITH TIME ZONE,
        num_empleado INTEGER,
        fechajuntafin TIMESTAMP(6) WITH TIME ZONE,
        PRIMARY KEY (id_junta),
        CONSTRAINT tbjuntas_fk1 FOREIGN KEY (num_empleado) REFERENCES "tb_empleado" ("num_empleado"
        )
    );
    
CREATE SEQUENCE seq_tb_juntas INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START WITH 1
CACHE 20 NO CYCLE;

