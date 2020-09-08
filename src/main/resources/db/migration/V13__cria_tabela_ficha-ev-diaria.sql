create table ficha_ev_diaria (
	id bigint unsigned not null auto_increment,
	ficha_admissao_id bigint unsigned unique,
	
	vl_pa_inferior int,
	vl_pa_superior int,
	vl_temperatura decimal,
	vl_saturacao int,
	vl_hgt int,
	vl_freq_cardiaca int,
	vl_freq_respiratoria int,
	
	primary key(id)
);

alter table ficha_ev_diaria add constraint fk_ficha_ev_diaria_ficha_admissao
foreign key (ficha_admissao_id) references ficha_admissao (id);