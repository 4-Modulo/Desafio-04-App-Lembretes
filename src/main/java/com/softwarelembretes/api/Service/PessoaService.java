package com.softwarelembretes.api.Service;

import com.softwarelembretes.api.DTO.LembreteDTO;
import com.softwarelembretes.api.Entity.Pessoa;
import com.softwarelembretes.api.Repository.PessoaRepository;
import com.softwarelembretes.api.DTO.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private  PessoaRepository pessoaRepository;


    public Pessoa findNome(String nomeCompleto) {
        Pessoa pessoas = pessoaRepository.findByNomeCompleto(nomeCompleto);
        if (pessoas != null) {
            return pessoas;
        } else {
            throw new RuntimeException("Nenhuma pessoa encontrada com o nome completo: " + nomeCompleto);
        }
    }
    public PessoaDTO findById(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            return convertToDTO(pessoaOptional.get());
        } else {
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }

    public PessoaDTO create(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNomeCompleto(pessoaDTO.getNomeCompleto());

        return convertToDTO(pessoaRepository.save(pessoa));
    }

    public PessoaDTO update(Long id, PessoaDTO pessoaAtualizadaDTO) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));

        pessoaExistente.setNomeCompleto(pessoaAtualizadaDTO.getNomeCompleto());

        return convertToDTO(pessoaRepository.save(pessoaExistente));
    }

    private PessoaDTO convertToDTO(Pessoa pessoa) {
        List<LembreteDTO> lembretesDTO = pessoa.getLembretes().stream()
                .map(lembrete -> new LembreteDTO(lembrete.getId(), lembrete.getDescricao(), lembrete.getPessoaId()))
                .collect(Collectors.toList());

        return new PessoaDTO(pessoa.getId(), pessoa.getNomeCompleto(), lembretesDTO);
    }

    public void delete(Long id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            pessoaRepository.delete(pessoaOptional.get());
        } else {
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }
}