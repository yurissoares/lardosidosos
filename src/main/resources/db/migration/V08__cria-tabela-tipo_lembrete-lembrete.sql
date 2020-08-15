create table tipo_lembrete (
	id bigint unsigned not null auto_increment,
	descricao varchar(255) not null,
	
  	primary key(id)
);

create table lembrete (
	id bigint unsigned not null auto_increment,
	data_entrega datetime not null,
	status_lembrete ENUM('PENDENTE', 'CONCLUIDO') not null,
	usuario_origem_id bigint unsigned not null,
	usuario_destino_id bigint unsigned,
	morador_id bigint unsigned,
	tipo_usuario ENUM('DIRETOR', 'ASSISTENTE_SOCIAL', 'ENFERMEIRO', 'TECNICO') not null,
	tipo_lembrete_id bigint unsigned not null,
	registro_saude_id bigint unsigned,
	obs varchar(255),
	
  	primary key(id)
);

alter table lembrete add constraint fk_lembrete_morador
foreign key (morador_id) references morador (id);

alter table lembrete add constraint fk_lembrete_usuario_origem
foreign key (usuario_origem_id) references usuario (id);

alter table lembrete add constraint fk_lembrete_usuario_destino
foreign key (usuario_destino_id) references usuario (id);

alter table lembrete add constraint fk_lembrete_tipo_lembrete
foreign key (tipo_lembrete_id) references tipo_lembrete (id);

alter table lembrete add constraint fk_lembrete_registro_saude
foreign key (registro_saude_id) references registro_saude (id);







