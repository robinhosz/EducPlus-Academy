package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.dto.ProfessorDTO;
import br.com.unifg.educplus.domain.entity.Professor;
import br.com.unifg.educplus.infra.repositories.ProfessorRepository;
import br.com.unifg.educplus.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;


    public Professor createProfessor(ProfessorDTO obj) {

        return professorRepository.save(builder(obj));
    }

    public Professor builder(ProfessorDTO obj){
        return Professor.builder()
                .nome(obj.getNome())
                .idade(obj.getIdade())
                .area(obj.getArea())
                .build();
    }
}