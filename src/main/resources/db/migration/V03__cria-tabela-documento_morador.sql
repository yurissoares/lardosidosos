create table documento_morador (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
	data_entrega datetime not null,
	tipo_documento_morador enum('CPF', 'RG', 'CTPS', 'TITULO_ELEITOR', 'PLANO_FUNERARIO', 'CERTIDAO_DE_NASCIMENTO',
	'CERTIDAO_DE_CASAMENTO', 'CERTIDAO_DE_OBITO_DO_CONJUGE', 'CARTAO_DO_BENEFICIO', 'CARTAO_DO_SUS', 'CONTRATO') not null,

	primary key(id)
);

alter table documento_morador add constraint fk_documento_morador_morador
foreign key (morador_id) references morador (id);
