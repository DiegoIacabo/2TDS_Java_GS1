package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record LicencaResponse(
        Long id,
        String situacao,
        String tipo,
        LocalDate dataEmissao,
        LocalDate dataValidade,
        PescadorResponse pescador
) {
}
