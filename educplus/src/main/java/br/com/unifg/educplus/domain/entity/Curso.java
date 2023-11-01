package br.com.unifg.educplus.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private Double qtdHoras;

    private Boolean isDisponivel;

    private Integer classificacao;

    private Boolean isConcluido;

    @ManyToMany(mappedBy = "cursos")
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "curso_professor",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id"))
    private List<Professor> professores = new ArrayList<>();

    @OneToOne(mappedBy = "curso")
    private Prova prova;

    @OneToMany(mappedBy = "curso")
    private List<Aula> aula = new ArrayList<>();

}
