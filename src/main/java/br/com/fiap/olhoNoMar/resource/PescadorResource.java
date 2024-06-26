package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.PescadorRequest;
import br.com.fiap.olhoNoMar.dto.response.PescadorResponse;
import br.com.fiap.olhoNoMar.entity.Pescador;
import br.com.fiap.olhoNoMar.service.PescadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/pescador")
public class PescadorResource implements ResourceDTO<PescadorRequest, PescadorResponse> {

    @Autowired
    private PescadorService service;

    @GetMapping
    public ResponseEntity<Collection<PescadorResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "rgp", required = false) String rgp,
            @RequestParam(name = "telefone", required = false) String telefone
    ){
        var pescador = Pescador.builder()
                .nome(nome)
                .rgp(rgp)
                .telefone(telefone)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Pescador> example = Example.of(pescador, matcher);
        Collection<Pescador> pescadores = service.findAll(example);

        if (Objects.isNull(pescadores))
            return ResponseEntity.notFound().build();

        var response = pescadores.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<PescadorResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<PescadorResponse> save(@RequestBody @Valid PescadorRequest r) {
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
