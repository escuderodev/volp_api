package br.com.escuderodev.vollapi.controller;

import br.com.escuderodev.vollapi.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
//    Trazer o repository como se fosse um atributo da classe controler para poder persistir um médico no banco de dados
    @Autowired
    private MedicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicos>> consultaMedicos(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao) {
//        adequando o método abaixo para devolver DadosListagemMedicos no lugar de Medico
        var page = repository.findAllByStatusTrue(paginacao).map(DadosListagemMedicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
//        repository recebe os dados do medico e salva no banco
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }
}
