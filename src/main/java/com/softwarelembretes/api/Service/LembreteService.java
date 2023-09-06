package com.softwarelembretes.api.Service;

import com.softwarelembretes.api.DTO.PessoaDTO;
import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Entity.Pessoa;
import com.softwarelembretes.api.Repository.LembreteRepository;
import com.softwarelembretes.api.DTO.LembreteDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LembreteService {
    @Autowired
    private LembreteRepository repository;

    @Autowired
    private PessoaService pessoaService;
    public Lembrete toLembrete(LembreteDTO lembreteDTO){
        Lembrete lembrete = new Lembrete();
        lembrete.setId(lembreteDTO.getId());
        lembrete.setDescricao(lembreteDTO.getDescricao());
        lembrete.setPessoaId(lembreteDTO.getPessoaId());
        return lembrete;
    }
    public LembreteDTO toLembreteDTO(Lembrete lembrete){
        LembreteDTO lembreteDTO= new LembreteDTO();
        lembreteDTO.setId(lembrete.getId());
        lembreteDTO.setDescricao(lembrete.getDescricao());
        lembreteDTO.setPessoaId(lembrete.getPessoaId());
        return lembreteDTO;
    }
    @Transactional
    public LembreteDTO post(LembreteDTO lembreteDTO) {
        Assert.notNull(lembreteDTO.getDescricao(), "Informe um recado!");
        Assert.notNull(lembreteDTO.getPessoaId(), "Informe a pessoa desse recado!");
        PessoaDTO pessoa = pessoaService.getById(lembreteDTO.getPessoaId().getId());
        Assert.notNull(pessoa, "Pessoa não existe!");
        return toLembreteDTO(repository.save(toLembrete(lembreteDTO)));
    }

    public List<LembreteDTO> findAll() {
        return repository.findAll().stream().map(this::toLembreteDTO).toList();
    }
    public LembreteDTO getById(Long id){
        Lembrete lembreteById = repository.findById(id).orElse(null);
        Assert.notNull(lembreteById, String.format("Lembrete com ID %s não existe!", id));
        return toLembreteDTO(lembreteById);
    }
    @Transactional
    public LembreteDTO update(Long id, LembreteDTO lembreteDTO){
        Lembrete lembreteById = repository.findById(id).orElse(null);
        Assert.notNull(lembreteById, String.format("Lembrete com ID %s não existe!", id));
        Assert.notNull(lembreteDTO.getPessoaId(), "Informe a pessoa desse recado!");
        PessoaDTO pessoa = pessoaService.getById(lembreteDTO.getPessoaId().getId());
        Assert.notNull(pessoa, "Pessoa não existe!");
        return toLembreteDTO(repository.save(toLembrete(lembreteDTO)));
    }
    @Transactional
    public void delete(Long id){
        Lembrete lembreteById = repository.findById(id).orElse(null);
        Assert.notNull(lembreteById, String.format("Lembrete com ID %s não existe!", id));
        repository.deleteById(id);
    }

    public List<LembreteDTO> getByNomePessoa(String nome) {
        PessoaDTO pessoa = pessoaService.getByNome(nome);
        Assert.notNull(pessoa, String.format("Pessoa com nome %s não existe!", nome));
        return repository.getByNomePessoa(nome).stream().map(this::toLembreteDTO).toList();
    }


}
