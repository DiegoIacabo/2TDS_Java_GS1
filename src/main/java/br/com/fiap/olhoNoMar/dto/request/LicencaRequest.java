package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LicencaRequest(

        @NotNull(message = "A situação da licença deve ser informada.")
        String situacao,

        @Size(max = 1, message = "Tipo de licença inválido.")
        @NotNull(message = "O tipo da licença deve ser informado.")
        String tipo,

        @NotNull(message = "A data de emissão deve ser informada.")
        LocalDate dataEmissao,

        @NotNull(message = "O pescador da licença deve ser informado.")
        AbstractRequest pescador
) {
}
