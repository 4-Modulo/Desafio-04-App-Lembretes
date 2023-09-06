package com.softwarelembretes.api.Repository;

import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    @Query("from Lembrete where pessoa.nome = :nomePessoa")
    public List<Lembrete> getByNomePessoa(@Param("nomePessoa") final String nomePessoa);
}
