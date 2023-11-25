package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.application.controller.AdminController;
import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.dto.ProfessorDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Professor;
import br.com.unifg.educplus.domain.entity.Prova;
import br.com.unifg.educplus.domain.exceptions.CursoNotFound;
import br.com.unifg.educplus.domain.exceptions.ProfessorNotFound;
import br.com.unifg.educplus.infra.repositories.CursoRepository;
import br.com.unifg.educplus.infra.repositories.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;
    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProfessorRepository professorRepository;

    public static final Prova PROVA = new Prova();

    Curso curso;
    CursoDTO cursoDTO;
    Optional<Curso> optionalCurso;

    Professor professor;
    ProfessorDTO professorDTO;
    Optional<Professor> optionalProfessor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startCurso();
        startProfessor();
    }

    @Test
    void whenFindByIdThenReturnAnCursoNotFound() {

        when(cursoRepository.findById(anyLong())).thenThrow(new CursoNotFound("Curso não encontrado"));

        try {
            CursoNotFound cursoNotFound = assertThrows(CursoNotFound.class, () -> cursoService.findById(2L));
        } catch (Exception ex) {
            assertEquals(CursoNotFound.class, ex.getClass());
            assertEquals("Curso não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenFindByIdThenReturnAnProfessorNotFound() {

        when(professorRepository.findById(anyLong())).thenThrow(new ProfessorNotFound("Professor não encontrado"));

        try {
            ProfessorNotFound professorNotFound = assertThrows(ProfessorNotFound.class, () -> cursoService.findByProfessorId(2L));
        } catch (Exception ex) {
            assertEquals(ProfessorNotFound.class, ex.getClass());
            assertEquals("Professor não encontrado", ex.getMessage());
        }
    }

    @Test
    void whenCreateThenReturnSucess() {
        when(cursoRepository.save(any())).thenReturn(curso);

        Curso response = cursoService.create(cursoDTO);

        assertNotNull(response);
        assertEquals(Curso.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Matematica", response.getTitulo());
        assertEquals("Curso de matematica", response.getDescricao());
        assertEquals(5, response.getClassificacao());
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(cursoRepository.findById(anyLong())).thenReturn(optionalCurso);
        when(cursoRepository.save(any())).thenReturn(curso);

        Curso responseFind = cursoService.findById(1L);
        Curso response = cursoService.update(cursoDTO);

    }

    @Test
    void deleteWithSuccess() {
        when(cursoRepository.findById(anyLong())).thenReturn(optionalCurso);
        doNothing().when(cursoRepository).deleteById(anyLong());
        cursoService.delete(1L);
        verify(cursoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void whenAddProfessorThenReturnSucess() {
        when(cursoRepository.findById(anyLong())).thenReturn(optionalCurso);
        when(professorRepository.findById(anyLong())).thenReturn(optionalProfessor);

        when(cursoRepository.save(any())).thenReturn(curso);

        String response = cursoService.addProfessor(1L, 1L);

        assertNotNull(response);
        assertEquals(String.class, response.getClass());
    }

    private void startCurso() {
        curso = new Curso(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true, new ArrayList<>(), new ArrayList<>(), PROVA, new ArrayList<>());
        cursoDTO = new CursoDTO(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true);
        optionalCurso = Optional.of(new Curso(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true, new ArrayList<>(), new ArrayList<>(), PROVA, new ArrayList<>()));
    }

    private void startProfessor() {
        professor = new Professor(1L, "Aderbal", 68, "Matematica", new ArrayList<>(), new ArrayList<>());
        professorDTO = new ProfessorDTO("Aderbal", 68, "Matematica");
        optionalProfessor = Optional.of(new Professor(1L, "Aderbal", 68, "Matematica", new ArrayList<>(), new ArrayList<>()));

    }
}