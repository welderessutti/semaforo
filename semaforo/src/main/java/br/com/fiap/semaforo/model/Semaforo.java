package br.com.fiap.semaforo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_semaforo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Semaforo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sq_semaforo"
    )
    @SequenceGenerator(
            name = "sq_semaforo",
            sequenceName = "sq_semaforo",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "loc_instalacao")
    private String localizacaoInstalacao;

    @Column(name = "tmp_verde")
    private Integer tempoVerde;

    @Column(name = "tmp_amarelo")
    private Integer tempoAmarelo;

    @Column(name = "tmp_vermelho")
    private Integer tempoVermelho;
}
