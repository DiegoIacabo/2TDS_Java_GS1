package br.com.fiap.olhoNoMar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ATIVIDADE")
public class Atividade {

    @Id
    @GeneratedValue(generator = "SQ_ATIVIDADE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ATIVIDADE", sequenceName = "SQ_ATIVIDADE", allocationSize = 1)
    @Column(name = "ID_ATIVIDADE")
    private Long id;

    private String local;

    private String localFinal;

    private LocalDate data;

    private String duracao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESCADOR",
            referencedColumnName = "ID_PESCADOR",
            foreignKey = @ForeignKey(
                    name = "FK_ATIVIDADE_PESCADOR"
            ),
            nullable = false
    )
    private Pescador pescador;
}
