package com.softwarelembretes.api.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nome;
    private List<LembreteDTO> lembretes;

    public PessoaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
