CREATE TABLE ConsultasMedicas.dbo.usuario (
    usuarioId BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER', -- Define um padrão para role, se aplicável.
    createdAt DATETIME NOT NULL DEFAULT GETDATE(), -- Data de criação
    updatedAt DATETIME NOT NULL DEFAULT GETDATE() -- Data de atualização
);

INSERT INTO ConsultasMedicas.dbo.usuario (nome, login, senha, role, createdAt, updatedAt)
VALUES ('Admin', 'admin', '$2a$10$h5YIU6sl2fh1IpUIpHkLt.DixHqJHBCGkABqJ706KbdgKfIPzmxNK', 'ADMIN', GETDATE(), GETDATE()); -- default password: admin@123
