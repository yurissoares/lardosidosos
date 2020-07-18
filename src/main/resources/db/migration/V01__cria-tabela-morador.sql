create table morador (
  id bigint unsigned not null auto_increment,
  nome varchar(50) not null,
  data_entrada datetime not null,
  data_nascimento datetime not null,
  estado_civil enum('CASADO', 'SOLTEIRO', 'OUTRO') not null,
  qtd_filhos int,
  naturalidade varchar(50),
  end_logradouro varchar(70) not null ,
  end_numero varchar(70) not null,
  end_bairro varchar(70) not null,
  end_cidade varchar(70) not null,
  end_estado enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO') not null,
  end_cep varchar(10) not null ,

  nm_cpf varchar(14) not null,
  nm_rg varchar(14) not null,
  nm_ctps varchar(50),
  nm_beneficio varchar(50),
  
  eh_aposentado boolean not null,
  tipo_aposentadoria enum('PRIVADA', 'PUBLICA', 'OUTRO') not null,
  tem_emprestimo boolean not null,
  medicacoes varchar(255),
  motivo_entrada varchar(255) not null,
  
  nome_responsavel varchar(50) not null,
  
  end_logradouro_responsavel varchar(70) not null,
  end_numero_responsavel varchar(70) not null,
  end_bairro_responsavel varchar(70) not null,
  end_cidade_responsavel varchar(70) not null,
  end_estado_responsavel enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO'),
  end_cep_responsavel varchar(10) not null,
  
  nm_cpf_responsavel varchar(14),
  nm_rg_responsavel varchar(14),
  tel_responsavel varchar(50),
  parentesco_responsavel enum('PAI', 'MAE', 'IRMAO', 'OUTRO') not null,
  obs_responsavel varchar(255),

  primary key(id)
);