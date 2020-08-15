create table contato (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
  	nome varchar(50) not null,
  	parentesco ENUM('PAI', 'IRMAO', 'SOBRINHO', 'FILHO', 'PRIMO', 'GENRO', 'NETO', 'VIZINHO', 'CONHECIDO', 'OUTRO') not null,
  	informacoes varchar(255),

  	primary key(id)
);

alter table contato add constraint fk_contato_morador
foreign key (morador_id) references morador (id);