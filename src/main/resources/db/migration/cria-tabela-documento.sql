create table documento (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
	data datetime,
	informacoes text,
	
	primary key(id)
);

alter table documento add constraint fk_documento_morador
foreign key (morador_id) references morador (id);