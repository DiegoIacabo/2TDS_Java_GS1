package br.com.fiap.olhoNoMar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(generator = "SQ_ANIMAL", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ANIMAL", sequenceName = "SQ_ANIMAL", allocationSize = 1)
    @Column(name = "ID_ANIMAL")
    private Long id;

    @Column(name = "NM_ANIMAL", nullable = false)
    private String nome;

    private String epocaReproducao;

    private String epocaPesca;

    private Double qtdPermitida;
}
