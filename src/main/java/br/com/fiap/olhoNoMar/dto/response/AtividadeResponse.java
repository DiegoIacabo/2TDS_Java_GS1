package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AtividadeResponse(

        Long id,
        String local,
        String localFinal,
        LocalDateTime inicio,
        LocalDateTime fim,
        PescadorResponse pescador
) {
}
