package com.example.GestaoDeBiblioteca.Services;

import com.example.GestaoDeBiblioteca.DTOs.Emprestimo.EmprestimoListagemDTO;
import com.example.GestaoDeBiblioteca.DTOs.Emprestimo.FazerEmprestimoDTO;
import com.example.GestaoDeBiblioteca.DTOs.GeralMessageDTO;
import com.example.GestaoDeBiblioteca.Entities.Clientes;
import com.example.GestaoDeBiblioteca.Entities.Emprestimo;
import com.example.GestaoDeBiblioteca.Entities.Livro;
import com.example.GestaoDeBiblioteca.Exceptions.ClienteNotFoundException;
import com.example.GestaoDeBiblioteca.Exceptions.LivroNotFoundException;
import com.example.GestaoDeBiblioteca.Repository.ClienteRepository;
import com.example.GestaoDeBiblioteca.Repository.EmprestimoRepository;
import com.example.GestaoDeBiblioteca.Repository.LivroRepository;
import org.springframework.stereotype.Service;
import com.example.GestaoDeBiblioteca.Security.JwtService;

import java.util.List;
import java.util.UUID;

@Service
public class EmprestimoService {
    private LivroRepository livroRepository;
    private ClienteRepository clienteRepository;
    private JwtService jwtSevice;
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoService(LivroRepository livroRepository, ClienteRepository clienteRepository, JwtService jwtSevice,  EmprestimoRepository emprestimoRepository) {
        this.livroRepository = livroRepository;
        this.clienteRepository = clienteRepository;
        this.jwtSevice = jwtSevice;
        this.emprestimoRepository = emprestimoRepository;
    }

    private Livro procurarLivro(String ibsn) throws LivroNotFoundException {
        Livro livro = livroRepository.findByISBN(ibsn);
        if (livro == null) {
            throw new LivroNotFoundException("Livro não encontrado");
        }

        return livro;
    }

    private Clientes procurarCliente(UUID id) throws ClienteNotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
    }

    private boolean validarEmprestimo(UUID idCliente, UUID idLivro) {
        boolean jaTemLivroAtivo =
                emprestimoRepository
                        .existsByClienteIdAndLivroIdAndEmprestadoTrue(idCliente, idLivro);

        if (jaTemLivroAtivo) {
            return false;
        }


        long quantidadeEmprestimosAtivos =
                emprestimoRepository.countByClienteIdAndEmprestadoTrue(idCliente);

        if (quantidadeEmprestimosAtivos >= 3) {
            return false;
        }

        return true;
    }




    public GeralMessageDTO fazerEmprestimo(FazerEmprestimoDTO fazerEmprestimoDTO) throws ClienteNotFoundException, LivroNotFoundException {
        UUID idCliente = jwtSevice.extrairId(fazerEmprestimoDTO.Token());
        Clientes clienteEncontrado = procurarCliente(idCliente);

        String isbn = fazerEmprestimoDTO.ISBN();

        Livro livroEncontrado = procurarLivro(isbn);

        boolean podeEmprestar = validarEmprestimo(idCliente, livroEncontrado.getId());

        if(!podeEmprestar) {
            return new GeralMessageDTO("Você ja atingiu o maximo de emprestimo ou já esta com esse livro ocupado");
        }

        Emprestimo novoEmprestimo = new Emprestimo(livroEncontrado, clienteEncontrado);
        emprestimoRepository.save(novoEmprestimo);

        return new GeralMessageDTO("Emprestimo feito com sucesso");
    };


    public GeralMessageDTO devolverLivro(FazerEmprestimoDTO dto)
            throws ClienteNotFoundException, LivroNotFoundException {

        UUID idCliente = jwtSevice.extrairId(dto.Token());
        Clientes cliente = procurarCliente(idCliente);

        Livro livro = procurarLivro(dto.ISBN());

        Emprestimo emprestimoAtivo = emprestimoRepository
                .findByClienteIdAndLivroIdAndEmprestadoTrue(cliente.getId(), livro.getId())
                .orElseThrow(() ->
                        new RuntimeException("Não existe empréstimo ativo desse livro para esse cliente")
                );

        emprestimoAtivo.setEmprestado(false);
        emprestimoRepository.save(emprestimoAtivo);

        return new GeralMessageDTO("Livro devolvido com sucesso");
    }


    public List<EmprestimoListagemDTO> listarEmprestimos() {

        List<Emprestimo> emprestimos = emprestimoRepository.findAll();

        return emprestimos.stream()
                .map(emprestimo -> {

                    String status = emprestimo.isEmprestado()
                            ? "Ainda emprestado"
                            : "Devolvido";

                    return new EmprestimoListagemDTO(
                            emprestimo.getCliente().getNome(),
                            emprestimo.getLivro().getTitulo(),
                            status
                    );
                })
                .toList();
    }
}
