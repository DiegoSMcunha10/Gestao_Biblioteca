package com.example.GestaoDeBiblioteca.Utils;

public class EscolherValorUtil {
    public static String escolherValor (String novo, String antigo) {
        return (novo != null && !novo.isBlank()) ? novo : antigo;
    }
}

