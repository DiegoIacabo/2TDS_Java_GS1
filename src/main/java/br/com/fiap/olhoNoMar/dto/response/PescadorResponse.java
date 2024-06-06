package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

@Builder
public record PescadorResponse(

        Long id,
        String nome,
        String rgp,
        String telefone
) {
}
