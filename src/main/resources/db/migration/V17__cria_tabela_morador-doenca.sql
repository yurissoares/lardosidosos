create table morador_doenca (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned,
	doenca_id bigint unsigned,

  	primary key(id)
);

alter table morador_doenca add constraint fk_morador_doenca_morador
foreign key (morador_id) references morador (id);

alter table morador_doenca add constraint fk_morador_doenca_doenca
foreign key (doenca_id) references doenca (id);