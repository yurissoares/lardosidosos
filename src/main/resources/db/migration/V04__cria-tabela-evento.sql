create table evento (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
	data datetime not null,
	tipo enum('ENTRADA', 'SAIDA', 'FALECIMENTO') not null,
	motivo varchar(255),
	observacoes varchar(255),
	
	primary key(id)
);

alter table evento add constraint fk_evento_morador
foreign key (morador_id) references morador (id);