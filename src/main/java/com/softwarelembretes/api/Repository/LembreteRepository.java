package com.softwarelembretes.api.Repository;

import com.softwarelembretes.api.Entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByPessoaId_Id(Long pessoaId);
    List<Lembrete> findByPessoaIdNomeCompleto(String nomeCompleto);
}
