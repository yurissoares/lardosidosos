create table tipo_registro_saude (
	id bigint unsigned not null auto_increment,
	descricao varchar(255) not null,
	
  	primary key(id)
);

create table registro_saude (
	id bigint unsigned not null auto_increment,
	dataEntrega datetime not null,
	descricao varchar(255),
	morador_id bigint unsigned not null,
	usuario_id bigint unsigned not null,
	tipo_registro_saude_id bigint unsigned not null,

  	primary key(id)
);

alter table registro_saude add constraint fk_registro_saude_morador
foreign key (morador_id) references morador (id);

alter table registro_saude add constraint fk_registro_saude_usuario
foreign key (usuario_id) references usuario (id);

alter table registro_saude add constraint fk_registro_saude_tipo_registro_saude
foreign key (tipo_registro_saude_id) references tipo_registro_saude (id);