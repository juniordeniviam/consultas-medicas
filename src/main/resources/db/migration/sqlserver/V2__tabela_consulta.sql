CREATE TABLE ConsultasMedicas.dbo.consulta (
	consultaID bigint IDENTITY(1,1) NOT NULL,
	datahora datetime NOT NULL,
	pacienteID bigint NOT NULL
);