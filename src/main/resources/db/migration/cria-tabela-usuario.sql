create table usuario (
  id bigint unsigned not null auto_increment,
  nome varchar(255) not null,
  email varchar(255),
  senha varchar(255) not null,

  primary key(id),
  unique key(email)
);