create table morador_antecedente (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned,
	antecedente_pessoal_id bigint unsigned,

  	primary key(id)
);

alter table morador_antecedente add constraint fk_morador_antecedente_morador
foreign key (morador_id) references morador (id);

alter table morador_antecedente add constraint fk_morador_antecedente_antecedente_pessoal
foreign key (antecedente_pessoal_id) references antecedente_pessoal (id);