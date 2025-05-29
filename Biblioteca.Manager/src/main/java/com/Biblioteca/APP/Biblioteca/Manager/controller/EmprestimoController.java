package com.Biblioteca.APP.Biblioteca.Manager.controller;



import java.util.List;
import java.util.Scanner;

import com.Biblioteca.APP.Biblioteca.Manager.model.EmprestimoModel;
import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;
import com.Biblioteca.APP.Biblioteca.Manager.service.EmprestimoService;

public class EmprestimoController {

    private final EmprestimoService emprestimoService = new EmprestimoService();
    private final Scanner scanner = new Scanner(System.in);
    private final UserModel usuarioLogado;
    private final UserController userController = new UserController();

    public EmprestimoController(UserModel usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    public void menuBibliotecario() throws Exception {
        System.out.println("---Funções de Empréstimo---");
        System.out.println("1. Cadastrar Empréstimo");
        System.out.println("2. Atualizar Empréstimo");
        System.out.println("3. Deletar Empréstimo");
        System.out.println("4. Listar Todos os Empréstimos");
        System.out.println("5. Buscar Empréstimo por ID");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> cadastrarEmprestimo();
            case 2 -> atualizarEmprestimo();
            case 3 -> deletarEmprestimo();
            case 4 -> listarEmprestimos();
            case 5 -> buscarEmprestimoPorId();
            case 6 -> {
                System.out.println("Saindo...");
                return;
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    public void cadastrarEmprestimo() throws Exception {
        System.out.print("ID do Livro: ");
        long Id_Livro = scanner.nextLong();
        scanner.nextLine();
        System.out.print("ID do Usuário: ");
        long usuarioId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data do Empréstimo (YYYY-MM-DD): ");
        String Data_Emprestimo = scanner.nextLine();
        System.out.println("Data de Devolução (YYYY-MM-DD): ");
        String Data_Prevista_Devolucao = scanner.nextLine();
        System.out.println("Emprestimo Cadadstrado com Sucesso!");

        EmprestimoModel novoEmprestimo = new EmprestimoModel();
        novoEmprestimo.setLivro_id(Id_Livro);
        novoEmprestimo.setData_emprestimo(Data_Emprestimo);
        novoEmprestimo.setUsuario_id(usuarioId);
        novoEmprestimo.setData_devolucao(Data_Prevista_Devolucao);

        emprestimoService.cadastrarEmprestimo(novoEmprestimo, usuarioLogado);
    }

    public void atualizarEmprestimo() throws Exception {
        System.out.print("ID do Empréstimo: ");
        long emprestimoId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("ID do Livro: ");
        long Id_Livro = scanner.nextLong();
        scanner.nextLine();
        System.out.print("ID do Usuário: ");
        long Id_Usuario = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Data do Empréstimo (YYYY-MM-DD): ");
        String Data_Emprestimo = scanner.nextLine();
        System.out.println("Data de Devolução (YYYY-MM-DD): ");
        String Data_Prevista_Devolucao = scanner.nextLine();
        System.out.println("Empréstimo Atualizado com Sucesso!");

        EmprestimoModel emprestimoAtualizado = new EmprestimoModel();
        emprestimoAtualizado.setId(emprestimoId);
        emprestimoAtualizado.setLivro_id(Id_Livro);
        emprestimoAtualizado.setData_emprestimo(Data_Emprestimo);
        emprestimoAtualizado.setData_devolucao(Data_Prevista_Devolucao);
        emprestimoAtualizado.setUsuario_id(Id_Usuario);

        emprestimoService.atualizarEmprestimo(emprestimoAtualizado, usuarioLogado);
    }

    public void deletarEmprestimo() throws Exception {
        System.out.print("ID do Empréstimo: ");
        long emprestimoId = scanner.nextLong();
        
        emprestimoService.deletarEmprestimo(emprestimoId, usuarioLogado);
    }

    public void listarEmprestimos() throws Exception {
        List<EmprestimoModel> emprestimos = emprestimoService.listarTodosEmprestimos();
        
        for (EmprestimoModel emprestimo : emprestimos) {
            System.out.println("ID: " + emprestimo.getId()
            + ", Livro ID: " + emprestimo.getLivro_id()
            + ", Data de Empréstimo: " + emprestimo.getData_emprestimo()
            + ", Data de Devolução: " + emprestimo.getData_devolucao());
        }
    }

    public void buscarEmprestimoPorId() throws Exception {
        System.out.print("ID do Empréstimo: ");
        long id = scanner.nextLong();
        EmprestimoModel emprestimo = emprestimoService.buscarEmprestimoPorId(id);
        if (emprestimo != null) {
            System.out.println("ID: " + emprestimo.getId()
            + ", Livro ID: " + emprestimo.getLivro_id()
            + ", Data de Empréstimo: " + emprestimo.getData_emprestimo()
            + ", Data de Devolução: " + emprestimo.getData_devolucao());
            
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void menuCliente() throws Exception {
    System.out.println("--- Histórico de Empréstimos ---");
    
    List<EmprestimoModel> emprestimos = emprestimoService.buscarEmprestimosPorId_Usuario(usuarioLogado.getId());


    if (emprestimos.isEmpty()) {
        System.out.println("Nenhum empréstimo encontrado.");
        userController.menuCliente();
    }

    for (EmprestimoModel emprestimo : emprestimos) {
        System.out.println("ID: " + emprestimo.getId()
            + ", Livro ID: " + emprestimo.getLivro_id()
            + ", Data de Empréstimo: " + emprestimo.getData_emprestimo()
            + ", Data de Devolução: " + emprestimo.getData_devolucao());
    }
}
}
