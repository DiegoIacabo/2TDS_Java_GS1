package br.com.fiap.olhoNoMar.service;

import br.com.fiap.olhoNoMar.dto.request.AnimalRequest;
import br.com.fiap.olhoNoMar.dto.response.AnimalResponse;
import br.com.fiap.olhoNoMar.entity.Animal;
import br.com.fiap.olhoNoMar.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnimalService implements ServiceDTO<Animal, AnimalRequest, AnimalResponse> {

    @Autowired
    private AnimalRepository repo;

    @Override
    public Animal toEntity(AnimalRequest animalRequest) {
        return Animal.builder()
                .nome(animalRequest.nome())
                .epocaReproducao(animalRequest.epocaReproducao())
                .epocaPesca(animalRequest.epocaPesca())
                .qtdPermitida(animalRequest.qtdPermitida())
                .build();
    }

    @Override
    public AnimalResponse toResponse(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .nome(animal.getNome())
                .epocaReproducao(animal.getEpocaReproducao())
                .epocaPesca(animal.getEpocaPesca())
                .qtdPermitida(animal.getQtdPermitida())
                .build();
    }

    @Override
    public Collection<Animal> findAll(Example<Animal> example) {
        return repo.findAll(example);
    }

    @Override
    public Animal findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Animal save(Animal animal) {
        return repo.save(animal);
    }
}
