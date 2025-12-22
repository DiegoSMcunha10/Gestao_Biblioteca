package com.example.GestaoDeBiblioteca.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "livro")
@Entity
@Getter
@Setter
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titulo;

    @Column(unique = true)
    private String ISBN;

    private String editora;
    private String dataLancamento; //Mudar isso aqui dps

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro(String titulo, Autor autor,  String ISBN, String editora, String dataLancamento) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.editora = editora;
        this.dataLancamento = dataLancamento;
    }

    public Livro() {}
}
