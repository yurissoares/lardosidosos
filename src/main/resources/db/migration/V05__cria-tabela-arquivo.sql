create table arquivo (
	id bigint unsigned not null auto_increment,
	documento_id bigint unsigned not null,
	tipo_arquivo varchar(255) not null,
	data longblob not null,
	
	primary key(id)
);

alter table arquivo add constraint fk_arquivo_documento
foreign key (documento_id) references documento (id);