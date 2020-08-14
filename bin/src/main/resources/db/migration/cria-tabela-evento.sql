create table evento (
	id bigint not null auto_increment,
	morador_id bigint not null,
	data datetime,
	tipo text,
	motivo text,
	observacoes text,
	
	primary key(id)
);

alter table evento add constraint fk_evento_morador
foreign key (morador_id) references morador (id);