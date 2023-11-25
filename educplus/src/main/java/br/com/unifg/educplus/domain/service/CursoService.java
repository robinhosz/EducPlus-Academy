package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Professor;
import br.com.unifg.educplus.domain.exceptions.CursoNotFound;
import br.com.unifg.educplus.domain.exceptions.ProfessorNotFound;
import br.com.unifg.educplus.infra.repositories.CursoRepository;
import br.com.unifg.educplus.infra.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Curso findById(Long id) {
        Optional<Curso> obj = cursoRepository.findById(id);
        return obj.orElseThrow(() -> new CursoNotFound("Curso não encontrado"));
    }

    public Professor findByProfessorId(Long id) {
        Optional<Professor> obj = professorRepository.findById(id);
        return obj.orElseThrow(() -> new ProfessorNotFound("Professor não encontrado"));
    }

   public Curso create(CursoDTO obj) {
       return cursoRepository.save(builder(obj));
   }

    public Curso update(CursoDTO obj) {
        Curso oldObj = findById(obj.getId());

        oldObj.setTitulo(obj.getTitulo());
        oldObj.setDescricao(obj.getDescricao());
        oldObj.setQtdHoras(obj.getQtdHoras());
        oldObj.setIsDisponivel(obj.getIsDisponivel());
        oldObj.setClassificacao(obj.getClassificacao());
        oldObj.setIsConcluido(obj.getIsConcluido());


        return cursoRepository.save(oldObj);
    }

    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    public String addProfessor(Long idCurso, Long idProfessor) {
        List<Professor> professores = new ArrayList<>();
        Curso curso = findById(idCurso);
        Professor professor = findByProfessorId(idProfessor);

        professores.add(professor);
        curso.setProfessores(professores);
        cursoRepository.save(curso);
        return "Professor adicionado com sucesso ao curso";
    }


    public Curso builder(CursoDTO obj){
       return Curso.builder()
               .titulo(obj.getTitulo())
               .descricao(obj.getDescricao())
               .isConcluido(obj.getIsConcluido())
               .isDisponivel(obj.getIsDisponivel())
               .qtdHoras(obj.getQtdHoras())
               .classificacao(obj.getClassificacao())
               .build();
   }

}
