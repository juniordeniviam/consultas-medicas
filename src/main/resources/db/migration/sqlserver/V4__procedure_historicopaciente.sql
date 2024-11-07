CREATE PROCEDURE sp_HistoricoPaciente
    @pacienteID BIGINT
AS
BEGIN
    SELECT 
        c.datahora,
        hp.medicoresponsavel,
        hp.diagnostico,
        hp.tratamentoprescrito
    FROM 
        historicopaciente hp
    INNER JOIN 
        consulta c ON c.consultaID = hp.consultaID
    WHERE 
        c.pacienteID = @pacienteID
    ORDER BY 
        c.datahora DESC;
END;