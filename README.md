
# Sistema de Gerenciamento de Biblioteca 

Sistema simples de gerenciamento de biblioteca desenvolvido em Java. Permite que bibliotecários realizem Cadastros, Atualizações e Deleções de usuários, livros e empréstimos, Usuários podem visualizar listas de Livros e históricos de Empréstimos. Sistema operado via terminal ( Linha de Comando).

## Funcionalidades 

- Cadastro de Usuários ( Bibliotecários/Clientes)
- Login de Usuário 
- Cadastro de Livros e Empréstimos 
- Atualização de Livros e Empréstimos 
- Deleção de Livros e Empréstimos
- Visualização do Histórico de Empréstimo
- Listagem de Livros 

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.5.0
- MySQL 8.0
- Paradigma de Programação Orientada a Objetos (POO)

##  Estrutura do Projeto 

```bashBiblioteca.Manager/
├── .mvn/                     # Arquivos do Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── Biblioteca/
│   │   │           └── APP/
│   │   │               └── Biblioteca/
│   │   │                   ├── controller/     # Camada de controle (menus, ações do usuário)
│   │   │                   ├── dao/            # Acesso a dados (simulado ou real)
│   │   │                   ├── model/          # Modelos de dados (entidades)
│   │   │                   ├── service/        # Lógica de negócio
│   │   │                   └── Application.java# Classe principal (método main)
│   │   └── resources/                         # Arquivos de configuração e recursos (se houver)
│
├── test/                                       # Testes automatizados (opcional)
│
├── target/                                     # Diretório de saída da compilação (gerado pelo Maven)
│
├── pom.xml                                     # Arquivo de configuração do Maven
├── README.md                                   # Documentação do projeto
```

# Autores

-Rodrigo Gouveia<br>-Thulio Leal<br>



## License

MIT License

Copyright (c) 2025 Biblioteca.Manager

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE