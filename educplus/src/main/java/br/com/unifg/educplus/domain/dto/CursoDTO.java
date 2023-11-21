package br.com.unifg.educplus.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {

    private Long id;
    private String titulo;

    private String descricao;

    private Double qtdHoras;

    private Boolean isDisponivel;

    private Integer classificacao;

    private Boolean isConcluido;
}
