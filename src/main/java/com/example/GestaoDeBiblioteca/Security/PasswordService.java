package com.example.GestaoDeBiblioteca.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String gerarBCrypt(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean verificarSenha(String senhaDigitada, String senhaHashDoBanco) {
        return passwordEncoder.matches(senhaDigitada, senhaHashDoBanco);
    }
}
