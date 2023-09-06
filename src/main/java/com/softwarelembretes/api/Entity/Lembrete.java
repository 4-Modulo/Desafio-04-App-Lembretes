package com.softwarelembretes.api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lembretes")
public class Lembrete {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private String recado;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    private Pessoa pessoa;

}
