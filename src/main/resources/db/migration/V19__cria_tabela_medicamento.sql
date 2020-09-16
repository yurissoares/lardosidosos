create table medicamento (
	id bigint unsigned not null auto_increment,
	nomenclatura varchar(255),
	estoque int,
	preco decimal,
	
  	primary key(id)
);