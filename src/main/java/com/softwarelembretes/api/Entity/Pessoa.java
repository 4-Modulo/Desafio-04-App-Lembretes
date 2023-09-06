package com.softwarelembretes.api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pessoa", schema = "public")
public class Pessoa {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "nomeCompleto")
    private String nome;
    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoaId")
    private List<Lembrete> lembretes = new ArrayList<>();


}
