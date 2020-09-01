create table ficha_saude (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
    usuario_id bigint unsigned not null,
	data datetime,
    tipo_ficha_saude enum('ADMISSAO', 'EVOLUCAO_DIARIA', 'EVENTOS_ADVERSOS'),
    nao_preenchida boolean,
    motivo_nao_preenchida enum('AGITACAO', 'AGRESSIVIDADE', 'INTERNAMENTO', 'CONSULTA_MEDICA', 'IDA_BANCO', 'IDA_INSS', 'OUTRA_AUSENCIA', 'OUTRO_MOTIVO'),

  	primary key(id)
);

alter table ficha_saude add constraint fk_ficha_saude_morador
foreign key (morador_id) references morador (id);

alter table ficha_saude add constraint fk_ficha_saude_usuario
foreign key (usuario_id) references usuario (id);