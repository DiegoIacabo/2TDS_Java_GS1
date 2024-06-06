package br.com.fiap.olhoNoMar.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AtividadeRequest(

        @NotNull(message = "O local de início deve ser informado.")
        String local,

        @NotNull(message = "O local final deve ser informado.")
        String localFinal,

        @NotNull(message = "A data de início da atividade é obrigatória.")
        LocalDateTime inicio,

        @NotNull(message = "A data do fim da atividade é obrigatória.")
        LocalDateTime fim
) {
}
