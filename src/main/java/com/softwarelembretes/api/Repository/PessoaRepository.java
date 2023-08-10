package com.softwarelembretes.api.Repository;

import com.softwarelembretes.api.Entity.Pessoa;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByNomeCompleto(String nomeCompleto);
}
