package br.com.unifg.educplus.domain.entity;

import br.com.unifg.educplus.domain.enums.DesempenhoEnum;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_prova")
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;

    @Enumerated(EnumType.STRING)
    private DesempenhoEnum desempenho;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}