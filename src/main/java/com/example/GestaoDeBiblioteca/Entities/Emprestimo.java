package com.example.GestaoDeBiblioteca.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "emprestimo")
@Entity
@Getter
@Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    Livro livro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientes_id", nullable = false)
    Clientes cliente;

    boolean emprestado = true;

    public Emprestimo(Livro livro, Clientes cliente) {
        this.livro = livro;
        this.cliente = cliente;
    }

    public Emprestimo() {}

}
