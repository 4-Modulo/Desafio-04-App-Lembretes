package com.softwarelembretes.api.Service;

import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Repository.LembreteRepository;
import com.softwarelembretes.api.DTO.LembreteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LembreteService {
    @Autowired
    private LembreteRepository lembreteRepository;


    public List<Lembrete> findAllByNomeCompleto(String nomeCompleto) {
            List<Lembrete> lembretes = lembreteRepository.findByPessoaIdNomeCompleto(nomeCompleto);

        return lembretes;
    }

    public LembreteDTO findById(Long id) {
        Optional<Lembrete> lembreteOptional = lembreteRepository.findById(id);
        if (lembreteOptional.isPresent()) {
            return convertToDTO(lembreteOptional.get());
        } else {
            throw new RuntimeException("Lembrete não encontrado com o ID: " + id);
        }
    }
    public Lembrete create(Lembrete lembrete) {

        return  lembreteRepository.save(lembrete);

    }
    public LembreteDTO update(Long id, LembreteDTO lembreteAtualizadoDTO) {
        Lembrete lembreteExistente = lembreteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado com o ID: " + id));

        lembreteExistente.setDescricao(lembreteAtualizadoDTO.getDescricao());

        Lembrete updatedLembrete = lembreteRepository.save(lembreteExistente);
        return convertToDTO(updatedLembrete);
    }

    public List<LembreteDTO> findByPessoaId(Long pessoaId) {
        List<Lembrete> lembretes = lembreteRepository.findByPessoaId_Id(pessoaId);

        return lembretes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private  LembreteDTO convertToDTO(Lembrete lembrete) {
        LembreteDTO lembreteDTO = new LembreteDTO();
        lembreteDTO.setId(lembrete.getId());
        lembreteDTO.setDescricao(lembrete.getDescricao());
        lembreteDTO.setPessoaId(lembrete.getPessoaId());
        return lembreteDTO;
    }

    public void delete(Long id) {
        Optional<Lembrete> lembreteOptional = lembreteRepository.findById(id);
        if (lembreteOptional.isPresent()) {
            lembreteRepository.delete(lembreteOptional.get());
        } else {
            throw new RuntimeException("Lembrete não encontrado com o ID: " + id);
        }
    }


}
