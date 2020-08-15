create table usuario (
  id bigint unsigned not null auto_increment,
  email varchar(50) not null,
  senha varchar(50) not null,
  tipo_usuario ENUM('DIRETOR', 'ASSISTENTE_SOCIAL', 'ENFERMEIRO', 'TECNICO'),
  nome_completo varchar(50),
  nome_resumido varchar(20),
  
  primary key(id)
);