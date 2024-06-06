package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;

public record AbstractRequest(

        @NotNull(message = "O ID deve ser informado.")
        Long id
) {
}
