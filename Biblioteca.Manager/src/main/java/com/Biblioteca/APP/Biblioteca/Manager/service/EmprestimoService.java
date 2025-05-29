package com.Biblioteca.APP.Biblioteca.Manager.service;

import java.sql.SQLException;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.dao.EmprestimoDAO;
import com.Biblioteca.APP.Biblioteca.Manager.model.EmprestimoModel;
import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;


public class EmprestimoService {
    private final EmprestimoDAO emprestimoDAO;

    public EmprestimoService() {
        this.emprestimoDAO = new EmprestimoDAO();
    }
    public void cadastrarEmprestimo(EmprestimoModel novoEmprestimo, UserModel usuarioLogado) throws SQLException {
        if (!usuarioLogado.getBibliotecario()) {
            throw new IllegalArgumentException("Apenas Bibliotecários são autorizados a cadastrar empréstimos.");
        }
        if (novoEmprestimo == null || 
            novoEmprestimo.getLivro_id() == 0 || 
            novoEmprestimo.getData_emprestimo() == null) {
            throw new IllegalArgumentException("Dados do empréstimo inválidos ou incompletos.");
        }
        emprestimoDAO.cadastrar(novoEmprestimo);
    }
    
    public void atualizarEmprestimo(EmprestimoModel emprestimoAtualizado, UserModel usuarioLogado) throws SQLException {
        if (!usuarioLogado.getBibliotecario()) {
            throw new IllegalArgumentException("Apenas Bibliotecários são autorizados a cadastrar empréstimos.");
        }
        if (emprestimoAtualizado == null || 
            emprestimoAtualizado.getId() <= 0 || 
            emprestimoAtualizado.getLivro_id() <= 0 || 
            emprestimoAtualizado.getData_emprestimo() == null) {
            throw new IllegalArgumentException("Dados do empréstimo inválidos ou incompletos.");
        }
        emprestimoDAO.atualizar(emprestimoAtualizado);
    }
    public void deletarEmprestimo(Long id, UserModel usuarioLogado) throws SQLException {
        if (!usuarioLogado.getBibliotecario()) {
            throw new IllegalArgumentException("Apenas Bibliotecários são autorizados a cadastrar empréstimos.");
        }
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do empréstimo inválido.");
        }
        emprestimoDAO.deletar(id);
    }
    public List<EmprestimoModel> listarTodosEmprestimos() throws SQLException {
        return emprestimoDAO.listarTodos();
    }
    public EmprestimoModel buscarEmprestimoPorId(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do empréstimo inválido.");
        }
        return emprestimoDAO.buscarPorId(id);
    }
    public List<EmprestimoModel> buscarEmprestimosPorId_Usuario(Long Id_Usuario) throws SQLException {
        if (Id_Usuario == null || Id_Usuario <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");

        }
        return emprestimoDAO.buscarPorId_Usuario(Id_Usuario);
    }
    public List<EmprestimoModel> buscarEmprestimosPorId_Livro(Long Id_Livro) throws SQLException {
        if (Id_Livro == null || Id_Livro <= 0) {
            throw new IllegalArgumentException("ID do livro inválido.");

        }
        EmprestimoModel emprestimo = emprestimoDAO.buscarPorId_Livro(Id_Livro);
        return emprestimo != null ? java.util.Collections.singletonList(emprestimo) : java.util.Collections.emptyList();
    }
    public List<EmprestimoModel> buscarEmprestimoData(long Data_Emprestimo) throws SQLException {
        if (Data_Emprestimo <= 0) {
            throw new IllegalArgumentException("Data do empréstimo inválida.");
        
        }
        return emprestimoDAO.listarPorData_Emprestimo(String.valueOf(Data_Emprestimo));
    }
    public List<EmprestimoModel> buscarEmprestimoData_Prevista_Devolucao(long Data_Prevista_Devolucao) throws SQLException {
        if (Data_Prevista_Devolucao <= 0) {
            throw new IllegalArgumentException("Data de devolução inválida.");

        }
        return emprestimoDAO.listarPorData_Prevista_Devolucao(String.valueOf(Data_Prevista_Devolucao));
    }

}
