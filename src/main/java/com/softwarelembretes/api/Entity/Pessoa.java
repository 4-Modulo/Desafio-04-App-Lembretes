package com.softwarelembretes.api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Pessoa", schema = "public")
public class Pessoa {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "nomeCompleto")
    private String nomeCompleto;
    @Getter @Setter
    @OneToMany
    @JoinColumn(name = "lembretes")
    private List<Lembrete> lembretes;



}
