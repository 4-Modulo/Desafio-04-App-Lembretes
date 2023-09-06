package com.softwarelembretes.api.Repository;

import com.softwarelembretes.api.Entity.Pessoa;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("from Pessoa where nome = :nome")
    public List<Pessoa> findByNome(@Param("nome") final String nome);
    @Query("from Pessoa where nome = :nome")
    public Pessoa findByNomeUnique(@Param("nome") final String nome);
}
