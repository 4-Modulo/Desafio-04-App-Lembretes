package com.softwarelembretes.api.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    private Long id;
    private String nomeCompleto;
    private List<LembreteDTO> lembretes;

    public PessoaDTO(Long id, String nomeCompleto) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
    }
}
