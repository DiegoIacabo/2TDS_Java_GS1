package br.com.fiap.olhoNoMar.service;

import org.springframework.data.domain.Example;

import java.util.Collection;

public interface ServiceDTO<Entity, Request, Response> {

    public Entity toEntity(Request request);

    public Response toResponse(Entity entity);

    public Collection<Entity> findAll(Example<Entity> example);

    public Entity findById(Long id);

    public Entity save(Entity entity);
}
