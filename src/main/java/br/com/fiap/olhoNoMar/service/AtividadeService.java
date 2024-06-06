package br.com.fiap.olhoNoMar.service;

import br.com.fiap.olhoNoMar.dto.request.AtividadeRequest;
import br.com.fiap.olhoNoMar.dto.response.AtividadeResponse;
import br.com.fiap.olhoNoMar.entity.Atividade;
import br.com.fiap.olhoNoMar.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AtividadeService implements ServiceDTO<Atividade, AtividadeRequest, AtividadeResponse> {

    @Autowired
    private AtividadeRepository repo;

    @Autowired
    private PescadorService pescadorService;

    @Override
    public Atividade toEntity(AtividadeRequest atividadeRequest) {

        var pescador = pescadorService.findById(atividadeRequest.pescador().id());

        return Atividade.builder()
                .local(atividadeRequest.local())
                .localFinal(atividadeRequest.localFinal())
                .inicio(atividadeRequest.inicio())
                .fim(atividadeRequest.fim())
                .pescador(pescador)
                .build();
    }

    @Override
    public AtividadeResponse toResponse(Atividade atividade) {

        var pescador = pescadorService.toResponse(atividade.getPescador());

        return AtividadeResponse.builder()
                .id(atividade.getId())
                .local(atividade.getLocal())
                .localFinal(atividade.getLocalFinal())
                .inicio(atividade.getInicio())
                .fim(atividade.getFim())
                .pescador(pescador)
                .build();
    }

    @Override
    public Collection<Atividade> findAll(Example<Atividade> example) {
        return repo.findAll(example);
    }

    @Override
    public Atividade findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Atividade save(Atividade atividade) {
        return repo.save(atividade);
    }
}
