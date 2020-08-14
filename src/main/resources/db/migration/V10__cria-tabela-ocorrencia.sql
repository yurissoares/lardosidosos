create table ocorrencia (
	id bigint unsigned not null auto_increment,
	morador_id bigint unsigned not null,
	data datetime not null,
	obs varchar(255),
	usuario_id bigint unsigned not null,
	tipo_ocorrencia_id bigint unsigned not null,
  	
  	primary key(id)
);

alter table ocorrencia add constraint fk_ocorrencia_morador
foreign key (morador_id) references morador (id);

alter table ocorrencia add constraint fk_ocorrencia_usuario
foreign key (usuario_id) references usuario (id);

alter table ocorrencia add constraint fk_ocorrencia_tipo_ocorrencia
foreign key (tipo_ocorrencia_id) references tipo_ocorrencia (id);