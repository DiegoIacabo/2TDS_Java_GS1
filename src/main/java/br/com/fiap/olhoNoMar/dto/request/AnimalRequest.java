package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;

public record AnimalRequest(

        @NotNull(message = "O nome é um campo obrigatório.")
        String nome,

        @NotNull(message = "A época de reprodução da espécie deve ser informada.")
        String epocaReproducao,

        @NotNull(message = "A época favorável de pesca da espécie deve ser informada.")
        String epocaPesca,

        @NotNull(message = "A quantidade permitida por pesca deve ser informada.")
        Double qtdPermitida
) {
}
