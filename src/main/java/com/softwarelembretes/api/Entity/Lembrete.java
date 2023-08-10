package com.softwarelembretes.api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Table(name = "Lembrete",schema = "public")
public class Lembrete {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "Desacricao")
    private String descricao;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoaId;
}
