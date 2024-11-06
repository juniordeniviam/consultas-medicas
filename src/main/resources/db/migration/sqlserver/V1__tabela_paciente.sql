CREATE TABLE ConsultasMedicas.dbo.paciente (
	pacienteID bigint IDENTITY(1,1) NOT NULL,
	cpf varchar(11) NOT NULL,
	nome varchar(255) NULL,
	sexo varchar(1) NULL,
	telefone varchar(20) NULL
);