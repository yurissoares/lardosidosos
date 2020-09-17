create table despesa (
	id bigint unsigned not null auto_increment,
	categoria_despesa ENUM('MEDICAMENTO', 'FRALDA', 'FISIOTERAPIA', 'INDIRETOS', 'HIGIENE_IDOSO', 'MEDICO'),
	descricao varchar(255),
	preco decimal,
	
  	primary key(id)
);