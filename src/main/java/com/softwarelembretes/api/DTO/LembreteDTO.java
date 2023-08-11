package com.softwarelembretes.api.DTO;

import com.softwarelembretes.api.Entity.Pessoa;
import lombok.*;

@Getter
@Setter
public class LembreteDTO {
    private Long id;
    private String descricao;
    private Pessoa pessoaId;

    public LembreteDTO(Long id, String descricao, Pessoa pessoa) {
        this.id = id;
        this.descricao = descricao;
        this.pessoaId = pessoa;
    }

    public LembreteDTO() {
    }

    public void setPessoaId(Pessoa pessoaId) {
    }
}
