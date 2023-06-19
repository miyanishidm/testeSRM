package br.com.testeSRM.controller;

import br.com.testeSRM.entity.Pessoa;
import br.com.testeSRM.repository.PessoaRepository;
import br.com.testeSRM.service.PessoaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa")
@CrossOrigin
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    private final PessoaService pessoaService;

    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @GetMapping("")
    public ResponseEntity<List<Pessoa>> list() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> get(@PathVariable("id") String id) {
        return pessoaRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("")
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.create(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> replace(@PathVariable("id") String id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.merge(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable("id") String id) {
        return pessoaRepository.findById(id).map(pessoa -> {
            pessoaRepository.delete(pessoa);
            return ResponseEntity.ok(pessoa);
        }).orElse(ResponseEntity.badRequest().build());
    }

}
