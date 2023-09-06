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
    private PessoaService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> post(@RequestBody PessoaDTO pessoa){
        try {
            return ResponseEntity.ok(service.post(pessoa));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(service.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try{
            return ResponseEntity.ok(service.getById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/nome")
    public ResponseEntity<?> getByNome(@RequestParam String nome){
        try{
            return ResponseEntity.ok(service.getByNome(nome));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody PessoaDTO pessoa){
        try{
            return ResponseEntity.ok(service.update(id, pessoa));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/deletar")
    public ResponseEntity<?> delete(@RequestParam Long id){
        try{
            service.delete(id);
            return ResponseEntity.ok(String.format("Pessoa com ID %s deletado com sucesso!", id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
