create table documento (
	id bigint not null auto_increment,
	morador_id bigint not null,
	informacoes text not null,
	
	primary key(id)
);

alter table documento add constraint fk_documento_morador
foreign key (morador_id) references morador (id);