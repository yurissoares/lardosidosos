create table morador_despesa (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned,
	despesa_id bigint unsigned,
	data datetime,
	quantidade int,
	preco_total decimal,
	

  	primary key(id)
);

alter table morador_despesa add constraint fk_morador_despesa_morador
foreign key (morador_id) references morador (id);

alter table morador_despesa add constraint fk_morador_despesa_despesa
foreign key (despesa_id) references despesa (id);