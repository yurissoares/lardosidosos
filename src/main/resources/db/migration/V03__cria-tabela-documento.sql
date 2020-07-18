create table documento (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
	data_entrega datetime not null,
	tipo_documento enum('CPF', 'RG', 'CTPS', 'TITULO_ELEITOR', 'PLANO_FUNERARIO', 'CERTIDA_DE_NASCIMENTO', 
	'CERTIDAO_DE_CASAMENTO', 'CERTIDAO_DE_OBITO_DO_CONJUGUE', 'CARTAO_DO_BENEFICIO', 'CARTAO_DO_SUS') not null,
	
	primary key(id)
);

alter table documento add constraint fk_documento_morador
foreign key (morador_id) references morador (id);