package br.com.testeSRM.controller;

import br.com.testeSRM.Application;
import br.com.testeSRM.entity.EnumTipoIdentificador;
import br.com.testeSRM.entity.Pessoa;
import br.com.testeSRM.repository.PessoaRepository;
import br.com.testeSRM.service.PessoaService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest({PessoaController.class, PessoaService.class})
public class PessoaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void listTest() throws Exception {
        var resultList = List.of(new Pessoa("testeCPF", "12345678910", EnumTipoIdentificador.CPF));
        Mockito.doReturn( resultList ).when(pessoaRepository).findAll();
        mvc.perform(get("/pessoa").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("testeCPF"))
                .andExpect(jsonPath("$[0].identificador").value("12345678910"))
                .andExpect(jsonPath("$[0].tipoIdentificador").value("CPF"))
                .andReturn();
        Mockito.verify(pessoaRepository).findAll();
    }

    @Test
    public void getTest() throws Exception {
        var resultPessoa = new Pessoa("testeCPF", "12345678910", EnumTipoIdentificador.CPF);
        Mockito.doReturn(Optional.of(resultPessoa) ).when(pessoaRepository).findById("1234");
        mvc.perform(get("/pessoa/1234").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.nome").value("testeCPF"))
                .andExpect(jsonPath("$.identificador").value("12345678910"))
                .andExpect(jsonPath("$.tipoIdentificador").value("CPF"))
                .andReturn();
        Mockito.verify(pessoaRepository).findById("1234");
    }
    @Test
    public void createTest() throws Exception {
        Mockito.doAnswer(T -> T.getArgument(0)).when(pessoaRepository).save(Mockito.any());
        mvc.perform(post("/pessoa").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                        "    \"nome\": \"testeCPF\",\n" +
                        "    \"identificador\": \"12345678910\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.nome").value("testeCPF"))
                .andExpect(jsonPath("$.identificador").value("12345678910"))
                .andExpect(jsonPath("$.tipoIdentificador").value("CPF"))
                .andReturn();
        Mockito.verify(pessoaRepository).save(Mockito.any());
    }

}
