package br.com.fiap.olhoNoMar.resource;

import br.com.fiap.olhoNoMar.dto.request.PescadorRequest;
import br.com.fiap.olhoNoMar.dto.response.PescadorResponse;
import br.com.fiap.olhoNoMar.entity.Pescador;
import br.com.fiap.olhoNoMar.service.PescadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

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


    }


    @Override
    public ResponseEntity<PescadorResponse> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PescadorResponse> save(PescadorRequest r) {
        return null;
    }
}
