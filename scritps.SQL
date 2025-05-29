CREATE DATABASE Biblioteca;
USE Biblioteca;



CREATE TABLE Usuarios (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Nome VARCHAR(255) NOT NULL CONSTRAINT chk_nome CHECK (Nome NOT LIKE '%[^a-zA-Z]%'),
    Email VARCHAR(255) UNIQUE NOT NULL CONSTRAINT chk_Email CHECK (Email LIKE '%@%.%'),
    Senha VARCHAR(255) NOT NULL,
    Bibliotecario BOOLEAN NOT NULL
);

CREATE TABLE Livros (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Autor VARCHAR(255) NOT NULL,
    Titulo VARCHAR(255) UNIQUE NOT NULL,
    Genero VARCHAR(100),
    Status_Emprestimo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Livros_Emprestados (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Id_Livro INT NOT NULL,
    Id_Usuario INT NOT NULL,
    Data_Emprestimo DATE NOT NULL,
    Data_Prevista_Devolucao DATE NOT NULL,
    
    CONSTRAINT fk_livro FOREIGN KEY (Id_Livro) REFERENCES Livros(Id),
    CONSTRAINT fk_usuario FOREIGN KEY (Id_Usuario) REFERENCES Usuarios(Id)
);

UPDATE Livros SET Status_Emprestimo = true WHERE Status_Emprestimo = 'Disponível';
UPDATE Livros SET Status_Emprestimo = false WHERE Status_Emprestimo = 'Indisponível';

ALTER TABLE Livros MODIFY Status_Emprestimo BOOLEAN;

DELIMITER //

CREATE TRIGGER alternar_status_livro
AFTER UPDATE ON Livros_Emprestados
FOR EACH ROW
BEGIN
    UPDATE Livros
    SET Status_Emprestimo = 
        CASE 
            WHEN Status_Emprestimo = TRUE THEN FALSE
            ELSE TRUE
        END
    WHERE Id = NEW.Id_Livro;
END;
//

DELIMITER ;

INSERT INTO Usuarios (Nome, Email, Senha, Bibliotecario)
VALUES ('Admin', 'admin@biblioteca.com', 'admin123', TRUE);

