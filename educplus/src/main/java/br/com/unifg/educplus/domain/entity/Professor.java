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
@Table(name = "tb_professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    private String area;

    @ManyToMany(mappedBy = "professores")
    private List<Curso> curso = new ArrayList<>();

    @ManyToMany(mappedBy = "professores")
    private List<Aluno> alunos = new ArrayList<>();
}
