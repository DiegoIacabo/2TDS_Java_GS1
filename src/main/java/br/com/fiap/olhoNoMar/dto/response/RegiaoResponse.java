package br.com.fiap.olhoNoMar.dto.response;

import lombok.Builder;

import java.util.Collection;

@Builder
public record RegiaoResponse(

        Long id,
        String nome,
        Collection<AnimalResponse> animais
) {
}
