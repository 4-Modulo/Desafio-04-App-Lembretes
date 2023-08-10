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

    public LembreteDTO findById(Long id) {
        Optional<Lembrete> lembreteOptional = lembreteRepository.findById(id);
        if (lembreteOptional.isPresent()) {
            return convertToDTO(lembreteOptional.get());
        } else {
            throw new RuntimeException("Lembrete não encontrado com o ID: " + id);
        }
    }
    public LembreteDTO create(LembreteDTO lembreteDTO) {
        Lembrete lembrete = new Lembrete();
        lembrete.setDescricao(lembreteDTO.getDescricao());

        Lembrete savedLembrete = lembreteRepository.save(lembrete);
        return convertToDTO(savedLembrete);
    }
    public LembreteDTO update(Long id, LembreteDTO lembreteAtualizadoDTO) {
        Lembrete lembreteExistente = lembreteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lembrete não encontrado com o ID: " + id));

        lembreteExistente.setDescricao(lembreteAtualizadoDTO.getDescricao());

        Lembrete updatedLembrete = lembreteRepository.save(lembreteExistente);
        return convertToDTO(updatedLembrete);
    }

    public List<LembreteDTO> findByPessoaId(Long pessoaId) {
        List<Lembrete> lembretes = lembreteRepository.findByPessoaId_Id(pessoaId); // Assuming you have a proper relationship between Pessoa and Lembrete

        return lembretes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<LembreteDTO> findByPessoaNomeCompleto(String nomeCompleto) {
        List<Lembrete> lembretes = lembreteRepository.findByPessoaId_NomeCompleto(nomeCompleto);

        return lembretes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }




    private LembreteDTO convertToDTO(Lembrete lembrete) {
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
