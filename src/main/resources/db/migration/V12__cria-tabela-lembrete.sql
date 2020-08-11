create table lembrete (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned,
	tipo_usuario ENUM('DIRETOR', 'ASSISTENTE_SOCIAL', 'ENFERMEIRO', 'TECNICO') not null,
	usuario_id bigint unsigned,
	obs varchar(255),
	status_lembrete ENUM('PENDENTE', 'CONCLUIDO') not null,
	data datetime not null,
	tipo_lembrete_id bigint unsigned not null,
  	
  	primary key(id)
);

alter table lembrete add constraint fk_lembrete_morador
foreign key (morador_id) references morador (id);

alter table lembrete add constraint fk_lembrete_usuario
foreign key (usuario_id) references usuario (id);

alter table lembrete add constraint fk_lembrete_tipo_lembrete
foreign key (tipo_lembrete_id) references tipo_lembrete (id);