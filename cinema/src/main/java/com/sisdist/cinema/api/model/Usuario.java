package com.sisdist.cinema.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeCompleto;

    @Column(unique = true)
    @Email
    private String email;

    private String senha;
    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;

    private String telefone;
    private String endereco;
    private String nivelAcesso;
    private LocalDate dataCadastro;
    private String status;

    public Usuario() {
    }
    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDate.now();
    }
    public Usuario(String nomeCompleto, String email, String senha, LocalDate dataNascimento, String cpf, String telefone, String endereco, String nivelAcesso, LocalDate dataCadastro, String status) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nivelAcesso = nivelAcesso;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
