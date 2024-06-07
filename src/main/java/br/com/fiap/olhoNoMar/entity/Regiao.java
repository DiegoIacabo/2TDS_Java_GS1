package br.com.fiap.olhoNoMar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_REGIAO")
public class Regiao {

    @Id
    @GeneratedValue(generator = "SQ_REGIAO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_REGIAO", sequenceName = "SQ_REGIAO", allocationSize = 1)
    @Column(name = "ID_REGIAO")
    private Long id;

    @Column(name = "NM_REGIAO", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_REGIAO_ANIMAL",
            joinColumns = {
                    @JoinColumn(
                            name = "REGIAO",
                            referencedColumnName = "ID_REGIAO",
                            foreignKey = @ForeignKey(name = "FK_REGIAO_ANIMAL")
                    )
            },
            inverseJoinColumns = {
                   @JoinColumn(
                            name = "ANIMAL",
                            referencedColumnName = "ID_ANIMAL",
                            foreignKey = @ForeignKey(name = "FK_ANIMAL_REGIAO")
                    )
            }
    )
    private Set<Animal> animais = new LinkedHashSet<>();
}
