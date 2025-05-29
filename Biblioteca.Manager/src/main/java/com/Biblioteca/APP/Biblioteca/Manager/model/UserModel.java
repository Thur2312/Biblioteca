package com.Biblioteca.APP.Biblioteca.Manager.model;

// Model é usado pra definir a estrutura de dados armazenados no banco de dados

public class UserModel {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean bibliotecario;


    //Getters

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }  
    public String getEmail() {
        return email;
    }
    public String getSenha() {
        return senha;
    }
    public Boolean getBibliotecario() {
        return bibliotecario;
    } 

    //Setters

    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setBibliotecario(Boolean bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
}
