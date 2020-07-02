SELECT * FROM lardosidosos.morador;

insert into morador
(
	nome, data_entrada, data_nascimento, estado_civil, qtd_filhos, naturalidade, endereco, cidade, cep, estado
) values
(
	'Yuri Soares', now(), now(), 'SOLTEIRO', 2, 'Eunápolis', 'Av. Dom Pedro 2', 'Eunápolis', '45820-081', 'Bahia'
);