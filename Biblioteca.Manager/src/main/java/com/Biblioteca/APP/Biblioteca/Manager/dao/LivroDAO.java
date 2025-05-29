package com.Biblioteca.APP.Biblioteca.Manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.model.LivroModel;

public class LivroDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Função para cadastrar livro
    public void cadastrar(LivroModel livro) throws SQLException {
        String sql = "INSERT INTO Livros (titulo, autor, Genero, Status_Emprestimo) VALUES (?, ?, ?, ?)";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getGenero());
            statement.setBoolean(4, livro.getStatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro");
            throw e;
        }
        
    }

    // Função para atualizar livro

    public void atualizar(LivroModel livro) throws SQLException {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, Genero = ?, Status_Emprestimo = ? WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, livro.getTitulo());
            statement.setString(2, livro.getAutor());
            statement.setString(3, livro.getGenero());
            statement.setBoolean(4, livro.getStatus());
            statement.setLong(5, livro.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro");
            throw e;
        }
    }

    // Função para deletar livro
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM Livros WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro");
            throw e;
        }
    }
    // Função para listar todos os livros
    public List<LivroModel> listarTodos() throws SQLException {
        String sql = "SELECT * FROM Livros";
        List<LivroModel> livros = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                LivroModel livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar livros");
            throw e;
        }
        return livros;
    }
    // Função para buscar livro por ID
    public LivroModel buscarPorId(long id) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE id = ?";
        LivroModel livro = null;
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por ID");
            throw e;
        }
        return livro;
    }
    // Função para buscar livro por título
    public LivroModel buscarPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE titulo = ?";
        LivroModel livro = null;
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, titulo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por título");
            throw e;
        }
        return livro;
    }
    // Função para buscar livro por autor
    public List<LivroModel> buscarPorAutor(String autor) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE autor = ?";
        List<LivroModel> livros = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, autor);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LivroModel livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por autor");
            throw e;
        }
        return livros;
    }
    // Função para buscar livro por gênero
    public List<LivroModel> buscarPorGenero(String genero) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE Genero = ?";
        List<LivroModel> livros = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, genero);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LivroModel livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por gênero");
            throw e;
        }
        return livros;
    }

    // Função para buscar livro por status
    public List<LivroModel> buscarPorStatus(Boolean status) throws SQLException {
        String sql = "SELECT * FROM Livros WHERE Status_Emprestimo = ?";
        List<LivroModel> livros = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setBoolean(1, status);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LivroModel livro = new LivroModel();
                livro.setId(resultSet.getLong("id"));
                livro.setTitulo(resultSet.getString("titulo"));
                livro.setAutor(resultSet.getString("autor"));
                livro.setGenero(resultSet.getString("Genero"));
                livro.setStatus(resultSet.getBoolean("Status_Emprestimo"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro por status");
            throw e;
        }
        return livros;
    }
}
