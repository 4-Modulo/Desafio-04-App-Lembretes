package com.softwarelembretes.api;

import com.softwarelembretes.api.DTO.LembreteDTO;
import com.softwarelembretes.api.DTO.PessoaDTO;
import com.softwarelembretes.api.Entity.Lembrete;
import com.softwarelembretes.api.Repository.LembreteRepository;
import com.softwarelembretes.api.Service.LembreteService;
import com.softwarelembretes.api.Service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class LembreteServiceTest {

    @Mock
    private LembreteRepository repository;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private LembreteService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByNomePessoa() {
        String nome = "John";
        Lembrete lembrete1 = new Lembrete();
        lembrete1.setId(1L);

        Lembrete lembrete2 = new Lembrete();
        lembrete2.setId(2L);
        List<Lembrete> lembretes = Arrays.asList(lembrete1, lembrete2);
        when(pessoaService.getByNome(nome)).thenReturn(new PessoaDTO());
        when(repository.getByNomePessoa(nome)).thenReturn(lembretes);
        List<LembreteDTO> result = service.getByNomePessoa(nome);
        Assert.notNull(result, "nao pode ser null");
        Assert.isTrue(result.size() == 2, "2 ");
    }

}

