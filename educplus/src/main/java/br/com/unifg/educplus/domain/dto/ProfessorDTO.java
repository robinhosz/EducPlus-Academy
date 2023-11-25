package br.com.unifg.educplus.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

    private String nome;

    private Integer idade;

    private String area;
}
