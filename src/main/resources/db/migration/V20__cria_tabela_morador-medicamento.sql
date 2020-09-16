create table morador_medicamento (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned,
	medicamento_id bigint unsigned,
	quantidade int,
	data datetime not null,

  	primary key(id)
);

alter table morador_medicamento add constraint fk_morador_medicamento_morador
foreign key (morador_id) references morador (id);

alter table morador_medicamento add constraint fk_morador_medicamento_medicamento
foreign key (medicamento_id) references medicamento (id);