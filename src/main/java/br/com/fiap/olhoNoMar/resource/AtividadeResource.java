package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.AtividadeRequest;
import br.com.fiap.olhoNoMar.dto.response.AtividadeResponse;
import br.com.fiap.olhoNoMar.entity.Atividade;
import br.com.fiap.olhoNoMar.entity.Pescador;
import br.com.fiap.olhoNoMar.service.AtividadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/atividade")
public class AtividadeResource implements ResourceDTO<AtividadeRequest, AtividadeResponse> {

    @Autowired
    private AtividadeService service;

    @GetMapping
    public ResponseEntity<Collection<AtividadeResponse>> findAll(
            @RequestParam(name = "local", required = false) String local,
            @RequestParam(name = "localFinal", required = false) String localFinal,
            @RequestParam(name = "inicio", required = false) LocalDateTime inicio,
            @RequestParam(name = "fim", required = false) LocalDateTime fim,
            @RequestParam(name = "pescador.nome", required = false) String pescadorNome,
            @RequestParam(name = "pescador.rgp", required = false) String pescadorRgp,
            @RequestParam(name = "pescador.Telefone", required = false) String pescadorTelefone
            ){
        var pescador = Pescador.builder()
                .nome(pescadorNome)
                .rgp(pescadorRgp)
                .telefone(pescadorTelefone)
                .build();

        var atividade = Atividade.builder()
                .local(local)
                .localFinal(localFinal)
                .inicio(inicio)
                .fim(fim)
                .pescador(pescador)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Atividade> example = Example.of(atividade, matcher);
        Collection<Atividade> atividades = service.findAll(example);

        if (Objects.isNull(atividades))
            return ResponseEntity.notFound().build();

        var response = atividades.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<AtividadeResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<AtividadeResponse> save(@RequestBody @Valid AtividadeRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
