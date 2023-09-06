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
    private LembreteService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> post(@RequestBody LembreteDTO lembrete){
        try {
            return ResponseEntity.ok(service.post(lembrete));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try{
            return ResponseEntity.ok(service.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/pessoa")
    public ResponseEntity<?> getByNomePessoa(@RequestParam String nome){
        try{
            return ResponseEntity.ok(service.getByNomePessoa(nome));
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
    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody LembreteDTO lembrete){
        try{
            return ResponseEntity.ok(service.update(id, lembrete));
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
