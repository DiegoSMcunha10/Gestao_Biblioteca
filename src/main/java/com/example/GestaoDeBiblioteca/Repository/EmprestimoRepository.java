package com.example.GestaoDeBiblioteca.Repository;

import com.example.GestaoDeBiblioteca.Entities.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, UUID> {


    List<Emprestimo> findByClienteIdAndLivroId(UUID clienteId, UUID livroId);


    Optional<Emprestimo>
    findByClienteIdAndLivroIdAndEmprestadoTrue(UUID clienteId, UUID livroId);


    long countByClienteIdAndEmprestadoTrue(UUID clienteId);


    boolean existsByClienteIdAndLivroIdAndEmprestadoTrue(UUID clienteId, UUID livroId);
}
