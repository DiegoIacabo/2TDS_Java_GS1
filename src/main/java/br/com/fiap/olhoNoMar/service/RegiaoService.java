package br.com.fiap.olhoNoMar.service;

import br.com.fiap.olhoNoMar.dto.request.RegiaoRequest;
import br.com.fiap.olhoNoMar.dto.response.RegiaoResponse;
import br.com.fiap.olhoNoMar.entity.Regiao;
import br.com.fiap.olhoNoMar.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RegiaoService implements ServiceDTO<Regiao, RegiaoRequest, RegiaoResponse> {

    @Autowired
    private RegiaoRepository repo;

    @Autowired
    private AnimalService animalService;

    @Override
    public Regiao toEntity(RegiaoRequest regiaoRequest) {
        return Regiao.builder()
                .nome(regiaoRequest.nome())
                .build();
    }

    @Override
    public RegiaoResponse toResponse(Regiao regiao) {

        var animais = regiao.getAnimais().stream().map(animalService::toResponse).toList();

        return RegiaoResponse.builder()
                .id(regiao.getId())
                .nome(regiao.getNome())
                .animais(animais)
                .build();
    }

    @Override
    public Collection<Regiao> findAll(Example<Regiao> example) {
        return repo.findAll(example);
    }

    @Override
    public Regiao findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Regiao save(Regiao regiao) {
        return repo.save(regiao);
    }
}
