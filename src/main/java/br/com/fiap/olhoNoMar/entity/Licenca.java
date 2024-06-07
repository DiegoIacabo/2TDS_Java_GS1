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
@Table(name = "TB_LICENCA")
public class Licenca {

    @Id
    @GeneratedValue(generator = "SQ_LICENCA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_LICENCA", sequenceName = "SQ_LICENCA", allocationSize = 1)
    @Column(name = "ID_LICENCA")
    private Long id;

    @Column(nullable = false)
    private String situacao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    private LocalDate dataValidade;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "PESCADOR",
            referencedColumnName = "ID_PESCADOR",
            foreignKey = @ForeignKey(
                    name = "FK_LICENCA_PESCADOR"
            ),
            nullable = false
    )
    private Pescador pescador;
}
