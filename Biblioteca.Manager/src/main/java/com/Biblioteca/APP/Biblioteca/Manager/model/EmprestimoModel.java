package com.Biblioteca.APP.Biblioteca.Manager.model;

public class EmprestimoModel {
private long id;
private long Id_Livro;
private long Id_Usuario;
private String data_emprestimo;
private String data_devolucao;

// Getters e Setters
public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public long getLivro_id() {
    return Id_Livro;
}

public void setLivro_id(long Id_Livro) {
    this.Id_Livro = Id_Livro;
}

public long getUsuario_id() {
    return Id_Usuario;
}

public void setUsuario_id(long Id_Usuario) {
    this.Id_Usuario = Id_Usuario;
}

public String getData_emprestimo() {
    return data_emprestimo;
}

public void setData_emprestimo(String data_emprestimo) {
    this.data_emprestimo = data_emprestimo;
}

public String getData_devolucao() {
    return data_devolucao;
}

public void setData_devolucao(String data_devolucao) {
    this.data_devolucao = data_devolucao;
}

@Override
public String toString() {
    return "LoanModel{" +
            "id=" + id +
            ", livro_id=" + Id_Livro +
            ", usuario_id=" + Id_Usuario +
            ", data_emprestimo='" + data_emprestimo + '\'' +
            ", data_devolucao='" + data_devolucao + '\'' +
            '}';
}
}
