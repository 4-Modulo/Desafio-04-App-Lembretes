package com.softwarelembretes.api.Controller;

import com.softwarelembretes.api.DTO.LembreteDTO;
import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Entity.Pessoa;
import com.softwarelembretes.api.Repository.LembreteRepository;
import com.softwarelembretes.api.Service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lembrete")
public class LembreteController {

    @Autowired
    private LembreteService lembreteService;
    @Autowired
    private LembreteRepository lembreteRepository;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final Lembrete lembrete = this.lembreteRepository.findById(id).orElse(null);

        return lembrete == null
                ? ResponseEntity.badRequest().body("Condutor não encontrado")
                : ResponseEntity.ok(lembrete);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody final Lembrete lembrete){
        try{
            this.lembreteService.create(lembrete);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
