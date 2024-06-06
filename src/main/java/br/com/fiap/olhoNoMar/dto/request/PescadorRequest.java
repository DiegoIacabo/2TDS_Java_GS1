package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PescadorRequest(

        @NotNull(message = "O nome é um campo obrigatório.")
        String nome,

        @NotNull(message = "O RGP é um campo obrigatório.")
        String rgp,

        @Size(max = 20, message = "Número de telefone inválido.")
        @NotNull(message = "O telefone deve ser informado.")
        String telefone
) {
}
