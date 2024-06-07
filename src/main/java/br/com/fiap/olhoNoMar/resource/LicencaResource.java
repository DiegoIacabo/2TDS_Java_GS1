package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.LicencaRequest;
import br.com.fiap.olhoNoMar.dto.response.LicencaResponse;
import br.com.fiap.olhoNoMar.entity.Licenca;
import br.com.fiap.olhoNoMar.entity.Pescador;
import br.com.fiap.olhoNoMar.service.LicencaService;
import br.com.fiap.olhoNoMar.service.PescadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/licenca")
public class LicencaResource implements ResourceDTO<LicencaRequest, LicencaResponse> {

    @Autowired
    private LicencaService service;

    @GetMapping
    public ResponseEntity<Collection<LicencaResponse>> findAll(
            @RequestParam(name = "situacao", required = false) String situacao,
            @RequestParam(name = "tipo", required = false) String tipo,
            @RequestParam(name = "dataEmissao", required = false) LocalDate dataEmissao,
            @RequestParam(name = "dataValidade", required = false) LocalDate dataValidade,
            @RequestParam(name = "pescador.nome", required = false) String pescadorNome,
            @RequestParam(name = "pescador.rgp", required = false) String pescadorRgp,
            @RequestParam(name = "pescador.Telefone", required = false) String pescadorTelefone
            ){
        var pescador = Pescador.builder()
                .nome(pescadorNome)
                .rgp(pescadorRgp)
                .telefone(pescadorTelefone)
                .build();

        var licenca = Licenca.builder()
                .situacao(situacao)
                .tipo(tipo)
                .dataEmissao(dataEmissao)
                .dataValidade(dataValidade)
                .pescador(pescador)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Licenca> example = Example.of(licenca, matcher);
        Collection<Licenca> licencas = service.findAll(example);

        if (Objects.isNull(licencas))
            return ResponseEntity.notFound().build();

        var response = licencas.stream().map(service::toResponse).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<LicencaResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity))
            return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<LicencaResponse> save(@RequestBody @Valid LicencaRequest r) {
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
