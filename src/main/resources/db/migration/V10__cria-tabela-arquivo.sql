create table arquivo (
	id bigint unsigned not null auto_increment,
	documento_morador_id bigint unsigned,
	documento_registro_saude_id bigint unsigned,
	tipo_arquivo varchar(255),
	data longblob not null,
	
	primary key(id)
);

alter table arquivo add constraint fk_arquivo_documento_morador
foreign key (documento_morador_id) references documento_morador (id);

alter table arquivo add constraint fk_arquivo_documento_registro_saude
foreign key (documento_registro_saude_id) references documento_registro_saude (id);