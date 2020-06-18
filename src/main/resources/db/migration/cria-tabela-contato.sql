create table contato (
	id bigint not null auto_increment,
	morador_id bigint not null,
  	nome text not null,
  	parentesco text not null,
  	informacoes text not null,
  	
  	primary key(id)
);

alter table contato add constraint fk_contato_morador
foreign key (morador_id) references morador (id);