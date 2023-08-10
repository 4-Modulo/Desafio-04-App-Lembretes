package com.softwarelembretes.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PessoaDTO {
    private Long id;
    private String nomeCompleto;

    public PessoaDTO(Long id, String nomeCompleto) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
    }

    // Outros construtores ou métodos de conversão se necessário
}
