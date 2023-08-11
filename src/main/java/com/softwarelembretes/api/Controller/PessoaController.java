package com.softwarelembretes.api.Controller;

import com.softwarelembretes.api.DTO.LembreteDTO;
import com.softwarelembretes.api.DTO.PessoaDTO;
import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Entity.Pessoa;
import com.softwarelembretes.api.Repository.PessoaRepository;
import com.softwarelembretes.api.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;



    @GetMapping("/{nomeCompleto}")
    public ResponseEntity<Pessoa> findByNomeCompleto(@PathVariable("nomeCompleto") final String nomeCompleto) {
        final Pessoa pessoa = this.pessoaService.findNome(nomeCompleto);
        return ResponseEntity.ok(pessoa);
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody final PessoaDTO pessoa){
        try{
            this.pessoaService.create(pessoa);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
