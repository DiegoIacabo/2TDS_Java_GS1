package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegiaoRequest(

        @NotNull(message = "O nome é um campo obrigatório.")
        String nome
) {
}
