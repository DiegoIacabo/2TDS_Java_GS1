package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.AnimalRequest;
import br.com.fiap.olhoNoMar.dto.response.AnimalResponse;
import br.com.fiap.olhoNoMar.entity.Animal;
import br.com.fiap.olhoNoMar.service.AnimalService;
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
@RequestMapping(value = "/animal")
public class AnimalResource implements ResourceDTO<AnimalRequest, AnimalResponse> {

    @Autowired
    private AnimalService service;

    @GetMapping
    public ResponseEntity<Collection<AnimalResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "epocaReproducao", required = false) String epocaReproducao,
            @RequestParam(name = "epocaPesca", required = false) String epocaPesca,
            @RequestParam(name = "qtdPermitida", required = false) Double qtdPermitida
    ){
        var animal = Animal.builder()
                .nome(nome)
                .epocaReproducao(epocaReproducao)
                .epocaPesca(epocaPesca)
                .qtdPermitida(qtdPermitida)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Animal> example = Example.of(animal, matcher);
        Collection<Animal> animais = service.findAll(example);

        if (Objects.isNull(animais))
            return ResponseEntity.notFound().build();

        var response = animais.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{id}")
    @Override
    public ResponseEntity<AnimalResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<AnimalResponse> save(@RequestBody @Valid AnimalRequest r) {
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
