ALTER TABLE morador MODIFY COLUMN nm_rg varchar(20) not null;
ALTER TABLE morador MODIFY COLUMN nm_rg_responsavel varchar(20);

ALTER TABLE morador add sexo ENUM('M', 'F') not null;

ALTER TABLE morador MODIFY COLUMN parentesco_responsavel ENUM('PAI', 'IRMAO', 'SOBRINHO', 'FILHO', 'PRIMO', 'GENRO', 'NETO', 'VIZINHO', 'CONHECIDO', 'OUTRO');

ALTER TABLE morador MODIFY tel_responsavel varchar(50);
ALTER TABLE morador MODIFY nome_responsavel varchar(50);