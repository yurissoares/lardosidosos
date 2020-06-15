create table tb_doc_entregue (
	id_doc_entregue bigint not null auto_increment,
	id_morador bigint not null,
	desc_doc_entregue text not null,
	
	primary key(id_doc_entregue)
);

alter table tb_doc_entregue add constraint fk_tb_doc_entregue_tb_morador
foreign key (id_morador) references tb_morador (id);