create table morador (
  id bigint unsigned not null auto_increment,
  nome varchar(50) not null,
  sexo ENUM('M', 'F'),
  data_entrada datetime not null,
  data_nascimento datetime not null,
  estado_civil enum('SOLTEIRO', 'CASADO', 'VIUVO', 'SEPARADO'),
  qtd_filhos int,
  naturalidade varchar(50),
  end_logradouro varchar(70),
  end_numero varchar(70),
  end_bairro varchar(70),
  end_cidade varchar(70),
  end_estado enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO'),
  end_cep varchar(10),

  nm_cpf varchar(14),
  nm_rg varchar(20) not null,
  nm_ctps varchar(50),
  nm_beneficio varchar(50),
  
  eh_aposentado boolean,
  tipo_aposentadoria enum('INSS', 'BPC', 'FUNRURAL', 'PENSIONISTA', 'PRIVADA'),
  
  tem_emprestimo boolean,
  valor_parcela_emprestimo double,
  data_ultima_parcela_emprestimo datetime,
  
  medicacoes varchar(255),
  motivo_ingresso varchar(255),
  
  situacao enum('ATIVO', 'INATIVO', 'FALECIDO'),
  
  nome_responsavel varchar(50),
  
  end_logradouro_responsavel varchar(70),
  end_numero_responsavel varchar(70),
  end_bairro_responsavel varchar(70),
  end_cidade_responsavel varchar(70),
  end_estado_responsavel enum('AC','AL','AP','AM','BA','CE','DF','ES','GO','MA','MT','MS','MG','PA','PB','PR','PE','PI','RJ','RN','RS','RO','RR','SC','SP','SE','TO'),
  end_cep_responsavel varchar(10),
  
  nm_cpf_responsavel varchar(14),
  nm_rg_responsavel varchar(20),
  tel_responsavel varchar(20),
  parentesco_responsavel ENUM('PAI', 'IRMAO', 'SOBRINHO', 'FILHO', 'PRIMO', 'GENRO', 'NETO', 'VIZINHO', 'CONHECIDO', 'OUTRO'),
  obs_responsavel varchar(255),

  primary key(id)
);