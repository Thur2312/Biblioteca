package com.Biblioteca.APP.Biblioteca.Manager.service;

// Responsavel por geranciar as regras de negócio relacionadas aos usuários

import java.sql.SQLException;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.dao.UserDAO;
import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;


public class UserService {
    
    private final UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }


    public void cadastrarUsuario(UserModel novoUsuario, UserModel usuarioLogado) throws Exception {
    if (!usuarioLogado.getBibliotecario()) {
        throw new Exception("Apenas bibliotecários podem cadastrar usuários.");
    }
    if (novoUsuario == null || 
        novoUsuario.getNome() == null || 
        novoUsuario.getEmail() == null || 
        novoUsuario.getSenha() == null || 
        novoUsuario.getBibliotecario() == null) {
        throw new IllegalArgumentException("Dados do usuário inválidos ou incompletos.");
    }
    if (novoUsuario.getNome().isEmpty() || 
        novoUsuario.getEmail().isEmpty() || 
        novoUsuario.getSenha().isEmpty()) {
        throw new IllegalArgumentException("Campos obrigatórios estão vazios");
    }
    userDAO.cadastro(novoUsuario);
}

    public UserModel autenticar(String email, String senha) throws SQLException {
        UserModel user = userDAO.buscarPorEmail(email);
        if (user != null && user.getSenha().equals(senha)) {
            return user;
        }
        throw new IllegalArgumentException("Email ou senha inválidos");
    }
    
    public void atualizarUsuario(UserModel usuarioAtualizado, UserModel usuarioLogado) throws Exception {
        if (!usuarioLogado.getBibliotecario()) {
            throw new Exception("Apenas bibliotecários podem atualizar usuários.");
        }
        userDAO.atualizar(usuarioAtualizado);
    }

    public void deletarUsuario(Long id, UserModel usuarioLogado) throws Exception {
        if (!usuarioLogado.getBibliotecario()) {
            throw new Exception("Apenas bibliotecários podem deletar usuários.");
        }
        userDAO.deletar(id);
    }

    public List<UserModel> listarTodos(UserModel usuarioLogado) throws Exception {
        if (!usuarioLogado.getBibliotecario()) {
            throw new Exception("Apenas bibliotecários podem ver todos os usuários.");
        }
        return userDAO.listarTodos();
    }

    public UserModel buscarPorId(long id, UserModel usuarioLogado) throws Exception {
        if (!usuarioLogado.getBibliotecario()) {
            throw new Exception("Apenas bibliotecários podem buscar usuários.");
        }
        return userDAO.buscarPorId(id);
    }

    public UserModel buscarPorEmail(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email está vazio ou nulo");
        }
        return userDAO.buscarPorEmail(email);
    }

}

