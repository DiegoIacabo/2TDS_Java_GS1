package br.com.fiap.olhoNoMar.service;

import br.com.fiap.olhoNoMar.dto.request.LicencaRequest;
import br.com.fiap.olhoNoMar.dto.response.LicencaResponse;
import br.com.fiap.olhoNoMar.entity.Licenca;
import br.com.fiap.olhoNoMar.repository.LicencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LicencaService implements ServiceDTO<Licenca, LicencaRequest, LicencaResponse> {

    @Autowired
    private LicencaRepository repo;

    @Autowired
    private PescadorService pescadorService;

    @Override
    public Licenca toEntity(LicencaRequest licencaRequest) {

        var pescador = pescadorService.findById(licencaRequest.pescador().id());

        return Licenca.builder()
                .situacao(licencaRequest.situacao())
                .tipo(licencaRequest.tipo())
                .dataEmissao(licencaRequest.dataEmissao())
                .pescador(pescador)
                .build();
    }

    @Override
    public LicencaResponse toResponse(Licenca licenca) {

        var pescador = pescadorService.toResponse(licenca.getPescador());

        return LicencaResponse.builder()
                .id(licenca.getId())
                .situacao(licenca.getSituacao())
                .tipo(licenca.getTipo())
                .dataEmissao(licenca.getDataEmissao())
                .dataValidade(licenca.getDataValidade())
                .pescador(pescador)
                .build();
    }

    @Override
    public Collection<Licenca> findAll(Example<Licenca> example) {
        return repo.findAll(example);
    }

    @Override
    public Licenca findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Licenca save(Licenca licenca) {
        return repo.save(licenca);
    }
}
