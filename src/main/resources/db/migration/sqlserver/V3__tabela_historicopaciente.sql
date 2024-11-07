CREATE TABLE ConsultasMedicas.dbo.historicopaciente (
	historicoID bigint IDENTITY(1,1) NOT NULL,
	consultaID bigint NOT NULL,
	datahistorico datetime NULL,
	medicoresponsavel varchar(150) NULL,
	diagnostico varchar(255) NULL,
	tratamentoprescrito varchar(255) NULL
);