package com.Biblioteca.APP.Biblioteca.Manager.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;
import com.Biblioteca.APP.Biblioteca.Manager.service.UserService;


// Responsável por receber as requisições HTTP e direcionar para os serviços correspondentes
public class UserController {
    private LivroController livroController;
    private EmprestimoController emprestimoController;


    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);
    private UserModel usuarioLogado;
    


    @SuppressWarnings("UnnecessaryReturnStatement")
    public void iniciar() throws SQLException, Exception {
            System.out.println("---Menu Biblioteca---");
            System.out.println("1. Login Usuário");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> fazerLogin();
                case 2 -> {
                    System.out.println("Saindo...");
                    break;
                }
                default -> System.out.println("Opção inválida.");
        }
    }

    private void fazerLogin() throws SQLException, Exception {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = userService.autenticar(email, senha); 

        if (usuarioLogado != null) {
            this.emprestimoController = new EmprestimoController(usuarioLogado);
            this.livroController = new LivroController(usuarioLogado, this.emprestimoController, this, new com.Biblioteca.APP.Biblioteca.Manager.service.LivroService());
        
            if (usuarioLogado.getBibliotecario()) {
                menuBibliotecario(livroController, emprestimoController);
            } else {
                menuCliente();
            }
        } else {
            System.out.println("Login inválido.");
        }
    }

    public void menuCliente() {
        while (true) {
            System.out.println("---Menu Cliente---");
            System.out.println("1. Ver Livros Disponíveis");
            System.out.println("2. Ver Histórico de Empréstimos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            

            switch (opcao) {
                    case 1 -> {
                    try {
                        new LivroController(usuarioLogado, emprestimoController, this, new com.Biblioteca.APP.Biblioteca.Manager.service.LivroService()).menuCliente(); 
                    } catch (Exception ex) {
                        System.out.println("Erro ao acessar os livros disponíveis: " + ex.getMessage());
                    }
                }
                    case 2 -> {
                        try {
                            new EmprestimoController(usuarioLogado).menuCliente(); 
                        } catch (Exception ex) {
                            System.out.println("Erro ao acessar o histórico de empréstimos: " + ex.getMessage());
                        }
                    }
                    
                    case 3 -> {
                        System.out.println("Saindo...");
                    }
                    default -> System.out.println("Opção inválida.");
                }
                break;
        }
        
    }

    public void menuBibliotecario(LivroController livroController, EmprestimoController emprestimoController) throws Exception {
        while (true) {
            System.out.println("---Menu Bibliotecário---");
            System.out.println("1. Funções de Usuário");
            System.out.println("2. Funções de Livro");
            System.out.println("3. Funções de Empréstimo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            

            switch (opcao) {
                    case 1 -> funcoesDeUsuario();
                    case 2 -> new LivroController(usuarioLogado, emprestimoController, this, new com.Biblioteca.APP.Biblioteca.Manager.service.LivroService()).menuBibliotecario();
                    case 3 -> new EmprestimoController(usuarioLogado).menuBibliotecario();
                    case 4 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
                if (opcao == 4) {
                    System.out.println("Saindo...");
                    return;
                }
                if (opcao < 1 || opcao > 4) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
        }   

    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public void funcoesDeUsuario() throws SQLException, Exception {
        System.out.println("---Funções de Usuário---");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Atualizar Usuário");
        System.out.println("3. Deletar Usuário");
        System.out.println("4. Listar Todos os Usuários");
        System.out.println("5. Buscar Usuário por ID");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> cadastrarUsuario();
            case 2 -> atualizarUsuario();
            case 3 -> deletarUsuario();
            case 4 -> listarTodosUsuarios();
            case 5 -> buscarUsuarioPorId();
            case 6 -> {
                System.out.println("Saindo...");
                menuBibliotecario(livroController, emprestimoController);
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void cadastrarUsuario() throws Exception {
        UserModel novo = new UserModel();
        System.out.print("Nome: ");
        novo.setNome(scanner.nextLine());
        System.out.print("Email: ");
        novo.setEmail(scanner.nextLine());
        System.out.print("Senha: ");
        novo.setSenha(scanner.nextLine());
        System.out.print("É bibliotecário? (true/false): ");
        novo.setBibliotecario(scanner.nextBoolean());
        scanner.nextLine();

        userService.cadastrarUsuario(novo, usuarioLogado);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void atualizarUsuario() throws Exception {
        System.out.print("ID do usuário: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        UserModel user = userService.buscarPorId(id, usuarioLogado);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        user.setNome(scanner.nextLine());
        System.out.print("Novo email: ");
        user.setEmail(scanner.nextLine());
        System.out.print("Nova senha: ");
        user.setSenha(scanner.nextLine());
        System.out.print("É bibliotecário? (true/false): ");
        user.setBibliotecario(scanner.nextBoolean());
        scanner.nextLine();

        userService.atualizarUsuario(user, usuarioLogado);
        System.out.println("Usuário atualizado com sucesso!");
    }

    private void deletarUsuario() throws Exception {
        System.out.print("ID do usuário: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        userService.deletarUsuario(id, usuarioLogado);
        System.out.println("Usuário deletado com sucesso!");
    }

    private void listarTodosUsuarios() throws Exception {
        var usuarios = userService.listarTodos(usuarioLogado);
        for (UserModel user : usuarios) {
            System.out.println("ID: " + user.getId() + ", Nome: " + user.getNome() + ", Email: " + user.getEmail() + ", Bibliotecário: " + user.getBibliotecario());
        }
    }

    private void buscarUsuarioPorId() throws Exception {
    System.out.print("ID do usuário: ");
    Long id = scanner.nextLong();
    scanner.nextLine();

    UserModel user = userService.buscarPorId(id, usuarioLogado);
        if (user != null) {
            System.out.println("ID: " + user.getId() + ", Nome: " + user.getNome() + ", Email: " + user.getEmail() + ", Bibliotecário: " + user.getBibliotecario());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }



}

