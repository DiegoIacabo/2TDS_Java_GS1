package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

@Builder
public record RegiaoResponse(

        Long id,
        String nome,
        AnimalResponse animais
) {
}
