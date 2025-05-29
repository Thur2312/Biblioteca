package com.Biblioteca.APP.Biblioteca.Manager.service;

import java.sql.SQLException;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.dao.LivroDAO;
import com.Biblioteca.APP.Biblioteca.Manager.model.LivroModel;
import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;


public class LivroService {
    private final LivroDAO livroDAO;
    

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public LivroDAO getLivroDAO() {
        return livroDAO;
    }

    public void cadastrarLivro(LivroModel novoLivro, UserModel usuarioLogado) throws SQLException {
        if (!usuarioLogado.getBibliotecario()) {
            throw new IllegalArgumentException("Apenas Bibliotecarios são autorizados a cadastrar livros.");
        }
        if (novoLivro == null || 
            novoLivro.getTitulo() == null || 
            novoLivro.getAutor() == null || 
            novoLivro.getGenero() == null) {
            throw new IllegalArgumentException("Dados do livro inválidos ou incompletos.");
        }
        if (novoLivro.getTitulo().isEmpty() || 
            novoLivro.getAutor().isEmpty() || 
            novoLivro.getGenero().isEmpty()) {
            throw new IllegalArgumentException("Campos obrigatórios estão vazios");
        }
        livroDAO.cadastrar(novoLivro);
    }
    public void atualizarLivro(LivroModel livroAtualizado) throws SQLException {
        if (livroAtualizado == null || 
            livroAtualizado.getId() <= 0 || 
            livroAtualizado.getTitulo() == null || 
            livroAtualizado.getAutor() == null || 
            livroAtualizado.getGenero() == null) {
            throw new IllegalArgumentException("Dados do livro inválidos ou incompletos.");
        }
        livroDAO.atualizar(livroAtualizado);
    }
    public void deletarLivro(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do livro inválido.");
        }
        livroDAO.deletar(id);
    }
    public List<LivroModel> listarTodosLivros() throws SQLException {
        return livroDAO.listarTodos();
    }
    public LivroModel buscarLivroPorId(Long id) throws SQLException {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do livro inválido.");
        }
        return livroDAO.buscarPorId(id);
    }
    public List<LivroModel> buscarLivrosPorTitulo(String titulo) throws SQLException {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Título do livro inválido.");
        }
        LivroModel livro = livroDAO.buscarPorTitulo(titulo);
        return livro != null ? java.util.Collections.singletonList(livro) : java.util.Collections.emptyList();
    }
    public List<LivroModel> buscarLivrosPorAutor(String autor) throws SQLException {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("Autor do livro inválido.");
        }
        return livroDAO.buscarPorAutor(autor);
    }
    public List<LivroModel> buscarLivrosPorGenero(String genero) throws SQLException {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("Gênero do livro inválido.");
        }
        return livroDAO.buscarPorGenero(genero);
    }
    public List<LivroModel> buscarLivrosPorStatus(Boolean status) throws SQLException {
        if (status == null) {
            throw new IllegalArgumentException("Status do livro inválido.");
        }
        return livroDAO.buscarPorStatus(status);
    }

    public void atualizarLivro(LivroModel livroAtualizado, UserModel usuarioLogado) {
        throw new UnsupportedOperationException("Unimplemented method 'atualizarLivro'");
    }
    
}
