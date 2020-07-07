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
  estado enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') not null,
  
  nm_cpf varchar(11),
  nm_rg varchar(10),
  nm_ctps varchar(255),
  nm_beneficio varchar(255),
  
  eh_aposentado boolean not null,
  tipo_aposentadoria enum('PRIVADA', 'PUBLICA', 'OUTRO') not null,
  tem_emprestimo boolean,
  medicacoes varchar(255),
  motivo_entrada varchar(255) not null,
  
  nome_responsavel varchar(255) not null,
  end_responsavel varchar(255) not null,
  cidade_responsavel varchar(255) not null,
  cep_responsavel varchar(255) not null,
  estado_responsavel enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') not null,
  nm_cpf_responsavel varchar(11),
  nm_rg_responsavel varchar(10),
  tel_responsavel varchar(255),
  parentesco_responsavel enum('PAI', 'MAE', 'IRMAO', 'OUTRO') not null,
  obs_responsavel varchar(255),

  primary key(id)
);