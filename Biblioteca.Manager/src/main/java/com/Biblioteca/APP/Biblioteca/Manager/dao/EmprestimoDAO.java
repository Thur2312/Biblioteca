package com.Biblioteca.APP.Biblioteca.Manager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Biblioteca.APP.Biblioteca.Manager.model.EmprestimoModel;

public class EmprestimoDAO {
   private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Função para cadastrar empréstimo
    public void cadastrar(EmprestimoModel emprestimo) throws SQLException {
        String sql = "INSERT INTO Livros_Emprestados (Id_Livro, Id_Usuario, Data_Emprestimo, Data_Prevista_Devolucao) VALUES (?, ?, ?, ?)";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, emprestimo.getLivro_id());
            statement.setLong(2, emprestimo.getUsuario_id());
            statement.setDate(3, java.sql.Date.valueOf(emprestimo.getData_emprestimo()));
            statement.setDate(4, java.sql.Date.valueOf(emprestimo.getData_devolucao()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar empréstimo");
            throw e;
        }
    }
    // Função para atualizar empréstimo
    public void atualizar(EmprestimoModel emprestimo) throws SQLException {
        String sql = "UPDATE Livros_Emprestados SET Id_Livro = ?, Id_Usuario = ?, Data_Emprestimo = ?, Data_Prevista_Devolucao = ? WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, emprestimo.getLivro_id());
            statement.setLong(2, emprestimo.getUsuario_id());
            statement.setDate(3, java.sql.Date.valueOf(emprestimo.getData_emprestimo()));
            statement.setDate(4, java.sql.Date.valueOf(emprestimo.getData_devolucao()));
            statement.setLong(5, emprestimo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo");
            throw e;
        }
    }
    // Função para deletar empréstimo
    public void deletar(Long id) throws SQLException {
        String sql = "DELETE FROM Livros_Emprestados WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar empréstimo");
            throw e;
        }
    }
    // Função para listar todos os empréstimos
    public List<EmprestimoModel> listarTodos() throws SQLException {
        List<EmprestimoModel> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Livros_Emprestados";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos");
            throw e;
        }
        return emprestimos;
    }
    // Função para buscar empréstimo por ID

    public EmprestimoModel buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM Livros_Emprestados WHERE id = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                return emprestimo;
            } else {
                return null; // Empréstimo não encontrado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimo por ID");
            throw e;
        }
    }

    // Função para buscar empréstimo por usuário ID

    public List<EmprestimoModel> buscarPorId_Usuario(Long Id_Usuario) throws SQLException {
    String sql = "SELECT * FROM Livros_Emprestados WHERE Id_Usuario = ?";
    List<EmprestimoModel> emprestimos = new ArrayList<>();

    try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = conexao.prepareStatement(sql)) {

        statement.setLong(1, Id_Usuario);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            EmprestimoModel emprestimo = new EmprestimoModel();
            emprestimo.setId(resultSet.getLong("id"));
            emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
            emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
            emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
            emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());

            emprestimos.add(emprestimo);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar empréstimos por usuário ID");
        throw e;
    }

    return emprestimos;
}

    // Função para buscar empréstimo por livro ID

    public EmprestimoModel buscarPorId_Livro(Long Id_Livro) throws SQLException {
        String sql = "SELECT * FROM Livros_Emprestados WHERE Id_Livro = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, Id_Livro);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                return emprestimo;
            } else {
                return null; // Empréstimo não encontrado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimo por livro ID");
            throw e;
        }
    }

    // Função para listar empréstimos por usuário ID  

    public List<EmprestimoModel> listarPorId_Usuario(Long Id_Usuario) throws SQLException {
        List<EmprestimoModel> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Livros_Emprestados WHERE Id_Usuario = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, Id_Usuario);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos por usuário ID");
            throw e;
        }
        return emprestimos;
    }

    // Função para listar empréstimos por livro ID

    public List<EmprestimoModel> listarPorId_Livro(Long Id_Livro) throws SQLException {
        List<EmprestimoModel> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Livros_Emprestados WHERE Id_Livro = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setLong(1, Id_Livro);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos por livro ID");
            throw e;
        }
        return emprestimos;
    }
    // Função para listar empréstimos por data de empréstimo
    public List<EmprestimoModel> listarPorData_Emprestimo(String Data_Emprestimo) throws SQLException {
        List<EmprestimoModel> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Livros_Emprestados WHERE Data_Emprestimo = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(Data_Emprestimo));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos por data de empréstimo");
            throw e;
        }
        return emprestimos;
    }
    // Função para listar empréstimos por data de devolução
    public List<EmprestimoModel> listarPorData_Prevista_Devolucao(String Data_Prevista_Devolucao) throws SQLException {
        List<EmprestimoModel> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM Livros_Emprestados WHERE Data_Prevista_Devolucao = ?";
        try (Connection conexao = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(Data_Prevista_Devolucao));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EmprestimoModel emprestimo = new EmprestimoModel();
                emprestimo.setId(resultSet.getLong("id"));
                emprestimo.setLivro_id(resultSet.getLong("Id_Livro"));
                emprestimo.setUsuario_id(resultSet.getLong("Id_Usuario"));
                emprestimo.setData_emprestimo(resultSet.getDate("Data_Emprestimo").toLocalDate().toString());
                emprestimo.setData_devolucao(resultSet.getDate("Data_Prevista_Devolucao").toLocalDate().toString());
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos por data de devolução");
            throw e;
        }
        return emprestimos;
    }
}
