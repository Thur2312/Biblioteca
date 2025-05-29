package com.Biblioteca.APP.Biblioteca.Manager.dao;

// Comunicação com o banco de dados, realiza as operações CRUD para usuários

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.model.UserModel;

public class UserDAO {
    //Url, user e senha do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Função para cadastrar usuario
    public void cadastro(UserModel user) throws SQLException {
        String sql = "INSERT INTO Usuarios (nome, email, senha, bibliotecario) VALUES (?, ?, ?, ?)";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {

                statement.setString(1, user.getNome());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getSenha());
                statement.setBoolean(4, user.getBibliotecario());
                statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuario");
            throw e;
        }
    }
    // Função para atualizar usuario
    public void atualizar(UserModel user) throws SQLException {
        String sql = "UPDATE Usuarios SET nome = ?, email = ?, senha = ?, bibliotecario = ? WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, user.getNome());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getSenha());
                statement.setBoolean(4, user.getBibliotecario());
                statement.setLong(5, user.getId());
                statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Erro ao atualizar usuario");
            throw e;
        }
    }

    // Função para deletar usuario
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setLong(1, id);
                statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Erro ao deletar usuario");
            throw e;
        }
    }

    // Função para login de usuario

    public UserModel login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND senha = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setString(1, email);
                statement.setString(2, senha);
                var resultSet = statement.executeQuery();

                if (resultSet.next()){
                    UserModel user = new UserModel();
                    user.setId(resultSet.getLong("id"));
                    user.setNome(resultSet.getString("nome"));
                    user.setEmail(resultSet.getString("email"));
                    user.setSenha(resultSet.getString("senha"));
                    user.setBibliotecario(resultSet.getBoolean("bibliotecario"));
                    return user;
                }
                else{
                    return null; // Retorna null se não encontrar o usuário
                }
        }
    }

    // Buscar usuario por id
    public UserModel buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {
                statement.setLong(1, id);
                var resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    UserModel user = new UserModel();
                    user.setId(resultSet.getLong("id"));
                    user.setNome(resultSet.getString("nome"));
                    user.setEmail(resultSet.getString("email"));
                    user.setSenha(resultSet.getString("senha"));
                    user.setBibliotecario(resultSet.getBoolean("bibliotecario"));
                    return user;
                }
                else{
                    return null; // Retorna null se não encontrar o usuário
                }
            }
        }
    
        // Listar todos os usuarios
    public List<UserModel> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<UserModel> usuarios = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql);
            var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getLong("id"));
                user.setNome(resultSet.getString("nome"));
                user.setEmail(resultSet.getString("email"));
                user.setSenha(resultSet.getString("senha"));
                user.setBibliotecario(resultSet.getBoolean("bibliotecario"));
                usuarios.add(user);
            }
        }
        return usuarios;
    }
    
    // Buscar usuario por email
    public UserModel buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE email = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, email);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserModel user = new UserModel();
                user.setId(resultSet.getLong("id"));
                user.setNome(resultSet.getString("nome"));
                user.setEmail(resultSet.getString("email"));
                user.setSenha(resultSet.getString("senha"));
                user.setBibliotecario(resultSet.getBoolean("bibliotecario"));
                return user;
            }
            else {
                return null; // Retorna null se não encontrar o usuário
            }
        }
    }
}