package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

@Builder
public record AnimalResponse(

        Long id,
        String nome,
        String epocaReproducao,
        String epocaPesca,
        Double qtdPermitida
) {
}
