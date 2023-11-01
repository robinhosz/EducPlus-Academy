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
@Table(name = "tb_aula")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String recursos;
    private Boolean isConcluida;
    private List<String> comentarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

}