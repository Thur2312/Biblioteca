package com.Biblioteca.APP.Biblioteca.Manager.controller;

import java.util.Scanner;

import com.Biblioteca.APP.Biblioteca.Manager.model.LivroModel;
import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;
import com.Biblioteca.APP.Biblioteca.Manager.service.LivroService;

public class LivroController {
    private final UserController userController;
    private final LivroService livroService;
    private final Scanner scanner = new Scanner(System.in);

    private final UserModel usuarioLogado;
    private final EmprestimoController emprestimoController;

    public LivroController(UserModel usuarioLogado, EmprestimoController emprestimoController, UserController userController, LivroService livroService) {
    this.usuarioLogado = usuarioLogado;
    this.emprestimoController = emprestimoController;
    this.userController = userController;
    this.livroService = livroService;
}

    @SuppressWarnings("UnnecessaryReturnStatement")
    public void menuBibliotecario() throws Exception {
        while (true) {
            System.out.println("---Funções de Livro---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Atualizar Livro");
            System.out.println("3. Deletar Livro");
            System.out.println("4. Listar Todos os Livros");
            System.out.println("5. Filtro de Livros");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> atualizarLivro();
                case 3 -> deletarLivro();
                case 4 -> listarLivros();
                case 5 -> filtrarLivros();
                case 6 -> {
                    System.out.println("Saindo...");
                    userController.menuBibliotecario(this, emprestimoController);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void cadastrarLivro() throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Gênero: ");
        String genero = scanner.nextLine();
        System.out.println("Status do Livro (Disponível = true, Indisponível = false): ");
        Boolean status = scanner.nextBoolean();
        scanner.nextLine(); 

        LivroModel novoLivro = new LivroModel();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setGenero(genero);
        novoLivro.setStatus(status);

        livroService.cadastrarLivro(novoLivro, usuarioLogado);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public void atualizarLivro() throws Exception {
        System.out.print("ID do Livro: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer

        LivroModel livroExistente = livroService.buscarLivroPorId(id);
        if (livroExistente == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.print("Novo Título ");
        String titulo = scanner.nextLine();

        System.out.print("Novo Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Novo Gênero: ");
        String genero = scanner.nextLine();

        System.out.println("Status do Livro(Disponivel = true, Indisponivel = false): ");
        Boolean status = scanner.nextBoolean();
        scanner.nextLine();

        LivroModel livroAtualizado = new LivroModel();
        livroAtualizado.setId(id);
        livroAtualizado.setTitulo(titulo);
        livroAtualizado.setAutor(autor);
        livroAtualizado.setGenero(genero);
        livroAtualizado.setStatus(status);

        livroService.atualizarLivro(livroAtualizado);
        

        System.out.println("Livro atualizado com sucesso!");
    }

    public void deletarLivro() throws Exception {
        System.out.print("ID do Livro: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        livroService.deletarLivro(id);
        System.out.println("Livro deletado com sucesso!");
    }

    public void listarLivros() throws Exception {
        var livros = livroService.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
            }
        }
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public void filtrarLivros() throws Exception {
        System.out.println("---Filtro de Livros---");
        System.out.println("1. Por Título");
        System.out.println("2. Por Autor");
        System.out.println("3. Por Gênero");
        System.out.println("4. Por Disponibilidade");
        System.out.println("5. Por ID");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> filtrarPorTitulo();
            case 2 -> filtrarPorAutor();
            case 3 -> filtrarPorGenero();
            case 4 -> filtrarPorStatus();
            case 5 -> filtrarPorId();
            case 6 -> {
                System.out.println("Saindo...");
                menuBibliotecario();
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void filtrarPorTitulo() throws Exception {
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();
        var livros = livroService.buscarLivrosPorTitulo(titulo);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
            }
        }
    }
    
    private void filtrarPorAutor() throws Exception {
        System.out.print("Autor do Livro: ");
        String autor = scanner.nextLine();
        var livros = livroService.buscarLivrosPorAutor(autor);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse autor.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
            }
        }
    }

    private void filtrarPorGenero() throws Exception {
        System.out.print("Gênero do Livro: ");
        String genero = scanner.nextLine();
        var livros = livroService.buscarLivrosPorGenero(genero);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse gênero.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
            }
        }
    }

    public void filtrarPorStatus() throws Exception {
        System.out.print("Status do Livro (Disponível = true, Indisponível = false): ");
        Boolean status = scanner.nextBoolean();
        scanner.nextLine();
        var livros = livroService.buscarLivrosPorStatus(status);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse status.");
        } else {
            for (LivroModel livro : livros) {
                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
            }
        }
    }

    public void filtrarPorId() throws Exception {
        System.out.print("ID do Livro: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        LivroModel livro = livroService.buscarLivroPorId(id);
        if (livro == null) {
            System.out.println("Nenhum livro encontrado com esse ID.");
        } else {
            System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", Gênero: " + livro.getGenero() + ", Status: " + (livro.getStatus() ? "Disponível" : "Indisponível"));
        }
    }

    public void menuCliente() throws Exception {
        while (true) {
            System.out.println("---Funções de Livro para Cliente---");
            System.out.println("1. Listar Todos os Livros");
            System.out.println("2. Filtrar Livros");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarLivros();
                case 2 -> filtrarLivros();
                case 3 -> {
                    System.out.println("Saindo...");
                    userController.menuCliente();
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}


   