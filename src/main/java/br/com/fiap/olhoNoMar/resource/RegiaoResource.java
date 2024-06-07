package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.AbstractRequest;
import br.com.fiap.olhoNoMar.dto.request.RegiaoRequest;
import br.com.fiap.olhoNoMar.dto.response.AnimalResponse;
import br.com.fiap.olhoNoMar.dto.response.RegiaoResponse;
import br.com.fiap.olhoNoMar.entity.Animal;
import br.com.fiap.olhoNoMar.entity.Regiao;
import br.com.fiap.olhoNoMar.service.AnimalService;
import br.com.fiap.olhoNoMar.service.RegiaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/regiao")
public class RegiaoResource implements ResourceDTO<RegiaoRequest, RegiaoResponse> {

    @Autowired
    private RegiaoService service;

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<Collection<RegiaoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "animal.nome", required = false) String animalNome,
            @RequestParam(name = "animal.epocaReproducao", required = false) String animalEpocaReproducao,
            @RequestParam(name = "animal.epocaPesca", required = false) String animalEpocaPesca,
            @RequestParam(name = "animal.qtdPermitida", required = false) Double animalQtdPermitida
    ){
        var animal = Animal.builder()
                .nome(animalNome)
                .epocaReproducao(animalEpocaReproducao)
                .epocaPesca(animalEpocaPesca)
                .qtdPermitida(animalQtdPermitida)
                .build();

        Set<Animal> collection = new LinkedHashSet<>();
        collection.add(animal);

        var regiao = Regiao.builder()
                .nome(nome)
                .animais(collection)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Regiao> example = Example.of(regiao, matcher);
        Collection<Regiao> regioes = service.findAll(example);

        if (Objects.isNull(regioes))
            return ResponseEntity.notFound().build();

        var response = regioes.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<RegiaoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<RegiaoResponse> save(@RequestBody @Valid RegiaoRequest r) {
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

    @PostMapping(value = "/{id}/animais")
    @Transactional
    public ResponseEntity<AnimalResponse> save(@PathVariable Long id, @RequestBody @Valid AbstractRequest animal){
        if (Objects.isNull(animal))
            return ResponseEntity.badRequest().build();

        var regiao = service.findById(id);
        if (Objects.isNull(regiao))
            return ResponseEntity.notFound().build();

        Animal animalEntity = null;
        if (Objects.nonNull(animal.id())){
            animalEntity = animalService.findById(animal.id());
        }
        regiao.getAnimais().add(animalEntity);

        var saved = animalService.save(animalEntity);
        var response = animalService.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
