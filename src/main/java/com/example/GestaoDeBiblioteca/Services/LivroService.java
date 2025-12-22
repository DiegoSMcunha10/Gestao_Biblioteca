package com.example.GestaoDeBiblioteca.Services;

import com.example.GestaoDeBiblioteca.DTOs.ConsultaTextoDTO;
import com.example.GestaoDeBiblioteca.DTOs.GeralMessageDTO;
import com.example.GestaoDeBiblioteca.Exceptions.AutorNotFoundException;
import com.example.GestaoDeBiblioteca.DTOs.Livro.LivroRequestDTO;
import com.example.GestaoDeBiblioteca.DTOs.Livro.LivroResponseDTO;
import com.example.GestaoDeBiblioteca.Entities.Autor;
import com.example.GestaoDeBiblioteca.Entities.Livro;
import com.example.GestaoDeBiblioteca.Exceptions.LivroNotFoundException;
import com.example.GestaoDeBiblioteca.Repository.AutorRepository;
import com.example.GestaoDeBiblioteca.Repository.LivroRepository;
import com.example.GestaoDeBiblioteca.Utils.EscolherValorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private Livro buscarLivro(String titulo) throws LivroNotFoundException {
        Livro livroEncontrado = livroRepository.findByTitulo(titulo);

        if (livroEncontrado == null) {
            throw  new LivroNotFoundException("Livro não encontrado");
        }

        return livroEncontrado;
    };

    public LivroResponseDTO criarLivro(LivroRequestDTO livro) throws AutorNotFoundException {
        Autor autor = autorRepository.findByNome(livro.nomeAutor().toUpperCase());

        if(autor == null){
            throw new AutorNotFoundException("Autor não encontrado, cadastre-o");
        }

        Livro novoLivro = new Livro(livro.titulo(), autor, livro.ISBN(), livro.editora(), livro.dataLancamento());
        livroRepository.save(novoLivro);

        return new LivroResponseDTO(novoLivro.getTitulo(), novoLivro.getEditora(),  novoLivro.getDataLancamento(), autor.getNome(), novoLivro.getISBN());
    }

    public GeralMessageDTO deletarLivro(ConsultaTextoDTO titulo) throws LivroNotFoundException {
        Livro livroEncontrado = buscarLivro(titulo.texto());
        livroRepository.delete(livroEncontrado);
        return new GeralMessageDTO("Livro deletado com sucesso");
    }

    public List<LivroResponseDTO> listarLivros() {
        return livroRepository.findAll()
                .stream()
                .map(
                livro -> new LivroResponseDTO(
                        livro.getTitulo(), livro.getEditora(), livro.getDataLancamento(), livro.getAutor().getNome(), livro.getISBN()
                )).toList();
    }

    public LivroResponseDTO atualizarLivro(LivroRequestDTO livro) throws LivroNotFoundException, AutorNotFoundException {
        Livro livroEncontrado = buscarLivro(livro.titulo());

        String nomeAutor = EscolherValorUtil.escolherValor(
                livro.nomeAutor(),
                livroEncontrado.getAutor().getNome()
        );

        Autor autor = autorRepository.findByNome(nomeAutor);


        if(autor == null){
            throw new AutorNotFoundException("Autor não encontrado, cadastre-o");
        }

        livroEncontrado.setAutor(autor);
        livroEncontrado.setISBN(EscolherValorUtil.escolherValor(livro.ISBN(), livroEncontrado.getISBN()));
        livroEncontrado.setEditora(EscolherValorUtil.escolherValor(livro.editora(), livroEncontrado.getEditora()));
        livroEncontrado.setDataLancamento(
                EscolherValorUtil.escolherValor(livro.dataLancamento(), livroEncontrado.getDataLancamento())
        );

        livroRepository.save(livroEncontrado);

        return new LivroResponseDTO(livroEncontrado.getTitulo(), livroEncontrado.getEditora(),  livroEncontrado.getDataLancamento(), livroEncontrado.getAutor().getNome(), livroEncontrado.getISBN());
    }

}
