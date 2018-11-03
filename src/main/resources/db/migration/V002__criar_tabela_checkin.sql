CREATE TABLE public.tb_checkin
(
  chk_id serial NOT NULL,
  chk_data_hora_entrada timestamp with time zone NOT NULL,
  chk_data_hora_saida timestamp with time zone NOT NULL,
  chk_valor numeric(19,2) NOT NULL,
  chk_tem_veiculo boolean NOT NULL,
  chk_observacoes character varying(500),
  chk_id_hospede integer REFERENCES tb_hospede(hpd_id),
  CONSTRAINT tb_checkin_chk_id_key UNIQUE (chk_id)
)
WITH (OIDS=FALSE);