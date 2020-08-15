create table documento_registro_saude (
	id bigint unsigned not null auto_increment,
	nome varchar(50) not null,
	data_entrega datetime not null,
	morador_id bigint unsigned not null,
	usuario_id bigint unsigned not null,
	registro_saude_id bigint unsigned not null,

	primary key(id)
);

alter table documento_registro_saude add constraint fk_documento_registro_saude_morador
foreign key (morador_id) references morador (id);

alter table documento_registro_saude add constraint fk_documento_registro_saude_registro_saude
foreign key (registro_saude_id) references registro_saude (id);
