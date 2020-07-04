create table morador (
  id bigint unsigned not null auto_increment,
  nome varchar(255) not null,
  data_entrada datetime not null,
  data_nascimento datetime not null,
  estado_civil enum('CASADO', 'SOLTEIRO', 'OUTRO') not null,
  qtd_filhos int,
  naturalidade varchar(255) ,
  endereco varchar(255) ,
  cidade varchar(255) ,
  cep varchar(255) ,
  estado varchar(255) ,

  primary key(id)
);