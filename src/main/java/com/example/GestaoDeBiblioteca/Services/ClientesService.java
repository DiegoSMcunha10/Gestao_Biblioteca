package com.example.GestaoDeBiblioteca.Services;

import com.example.GestaoDeBiblioteca.DTOs.Cliente.ClienteRequestDTO;
import com.example.GestaoDeBiblioteca.DTOs.Cliente.ClienteResponseDTO;
import com.example.GestaoDeBiblioteca.DTOs.ConsultaIdDTO;
import com.example.GestaoDeBiblioteca.DTOs.GeralMessageDTO;
import com.example.GestaoDeBiblioteca.DTOs.LoginDTO;
import com.example.GestaoDeBiblioteca.DTOs.TokenDTO;
import com.example.GestaoDeBiblioteca.Entities.Clientes;
import com.example.GestaoDeBiblioteca.Exceptions.ClienteNotFoundException;
import com.example.GestaoDeBiblioteca.Repository.ClienteRepository;
import com.example.GestaoDeBiblioteca.Security.PasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.GestaoDeBiblioteca.Security.JwtService;

import java.util.UUID;

@Service
public class ClientesService {
    private final ClienteRepository clienteRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public ClientesService(ClienteRepository clienteRepository, PasswordService passwordService, JwtService jwtService) {
        this.clienteRepository = clienteRepository;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public TokenDTO fazerLogin(LoginDTO loginDTO) throws ClienteNotFoundException {

        Clientes clienteEncontrado = clienteRepository.findByEmail(loginDTO.email());

        if (clienteEncontrado == null){
            throw new ClienteNotFoundException("Cliente não encontrado");
        }

        if (!passwordService.verificarSenha(loginDTO.senha(), clienteEncontrado.getSenha())) {
            return null;
        }

        String token = jwtService.gerarToken(clienteEncontrado.getId(), clienteEncontrado.getEmail(), clienteEncontrado.getNome());

        return new TokenDTO(token);
    }


    public ClienteResponseDTO criarCliente(ClienteRequestDTO cliente) {
        Clientes novoCliente = new Clientes(cliente.nome(), passwordService.gerarBCrypt(cliente.senha()), cliente.email());
        clienteRepository.save(novoCliente);
        return new ClienteResponseDTO(novoCliente.getNome(), novoCliente.getEmail());
    }

    public GeralMessageDTO deletarCliente(TokenDTO token) throws ClienteNotFoundException {
        UUID idCliente = jwtService.extrairId(token.token());
        System.out.println("idCliente: " + idCliente);

        Clientes clienteEncontrado = clienteRepository.findById(idCliente).orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
        clienteRepository.delete(clienteEncontrado);
        return new GeralMessageDTO("Cliente deletado com sucesso!");
    }
}
