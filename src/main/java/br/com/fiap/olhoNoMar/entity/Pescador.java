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
@Table(name = "TB_PESCADOR", uniqueConstraints = {
        @UniqueConstraint(
                name = "UK_RGP",
                columnNames = "RGP"
        )
})
public class Pescador {

    @Id
    @GeneratedValue(generator = "SQ_PESCADOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PESCADOR", sequenceName = "SQ_PESCADOR", allocationSize = 1)
    @Column(name = "ID_PESCADOR")
    private Long id;

    @Column(name = "NM_PESCADOR", nullable = false)
    private String nome;

    @Column(name = "RGP", nullable = false)
    private String rgp;

    @Column(name = "TELEFONE", nullable = false)
    private String telefone;
}
