create table morador (
  id bigint not null auto_increment,
  nome text not null,
  data_entrada datetime,
  data_nascimento datetime,
  estado_civil text,
  qtd_filhos int,
  naturalidade text,
  endereco text,
  cidade text,
  cep text,
  estado text,

  primary key(id)
);