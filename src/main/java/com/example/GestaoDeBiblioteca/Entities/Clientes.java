package com.example.GestaoDeBiblioteca.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Table(name = "clientes")
@Entity
@Getter
@Setter
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String nome;
    String senha;

    @Column(unique = true)
    String email;

    public Clientes() {}

    public Clientes(String nome, String senha, String email) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

}
