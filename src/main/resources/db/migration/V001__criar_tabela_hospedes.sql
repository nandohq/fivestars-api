CREATE TABLE public.tb_hospede
(
  hpd_id serial NOT NULL,
  hpd_nome character varying(50) NOT NULL,
  hpd_documento character varying(14) NOT NULL,
  hpd_telefone character varying(14) NOT NULL,
  CONSTRAINT tb_hospede_hpd_documento_key UNIQUE (hpd_documento),
  CONSTRAINT tb_hospede_hpd_id_key UNIQUE (hpd_id)
)
WITH (OIDS=FALSE);