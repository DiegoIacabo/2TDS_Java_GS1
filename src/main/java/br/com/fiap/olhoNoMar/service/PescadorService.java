package br.com.fiap.olhoNoMar.service;

import br.com.fiap.olhoNoMar.dto.request.PescadorRequest;
import br.com.fiap.olhoNoMar.dto.response.PescadorResponse;
import br.com.fiap.olhoNoMar.entity.Pescador;
import br.com.fiap.olhoNoMar.repository.PescadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PescadorService implements ServiceDTO<Pescador, PescadorRequest, PescadorResponse> {

    @Autowired
    private PescadorRepository repo;

    @Override
    public Pescador toEntity(PescadorRequest pescadorRequest) {
        return Pescador.builder().nome(pescadorRequest.nome()).rgp(pescadorRequest.rgp()).telefone(pescadorRequest.telefone()).build();
    }

    @Override
    public PescadorResponse toResponse(Pescador pescador) {
        return PescadorResponse.builder().id(pescador.getId()).nome(pescador.getNome()).rgp(pescador.getRgp()).telefone(pescador.getTelefone()).build();
    }

    @Override
    public Collection<Pescador> findAll(Example<Pescador> example) {
        return repo.findAll(example);
    }

    @Override
    public Pescador findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Pescador save(Pescador pescador) {
        return repo.save(pescador);
    }
}
