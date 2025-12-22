package com.example.GestaoDeBiblioteca.Controllers;

import com.example.GestaoDeBiblioteca.DTOs.Cliente.ClienteRequestDTO;
import com.example.GestaoDeBiblioteca.DTOs.ConsultaIdDTO;
import com.example.GestaoDeBiblioteca.DTOs.LoginDTO;
import com.example.GestaoDeBiblioteca.DTOs.TokenDTO;
import com.example.GestaoDeBiblioteca.Exceptions.ClienteNotFoundException;
import com.example.GestaoDeBiblioteca.Services.ClientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClientesService clientesService;

    public ClienteController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }


    @GetMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody LoginDTO loginDTO) throws ClienteNotFoundException {
        return ResponseEntity.ok(clientesService.fazerLogin(loginDTO));
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarCliente(@RequestBody ClienteRequestDTO clienteRequestDTO) {
        return ResponseEntity.ok(clientesService.criarCliente(clienteRequestDTO));
    }

    @DeleteMapping("/deletar") //trocar para o JWT
    public ResponseEntity<?> deletarCliente(@RequestBody TokenDTO token) throws ClienteNotFoundException {
        return ResponseEntity.ok(clientesService.deletarCliente(token));
    }
}
