package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.domain.dto.ProfessorDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Professor;
import br.com.unifg.educplus.infra.repositories.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService professorService;

    @Mock
    private ProfessorRepository professorRepository;

    Professor professor;
    ProfessorDTO professorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startProfessor();
    }

    @Test
    void whenCreateProfessorThenReturnSucess() {
        when(professorRepository.save(any())).thenReturn(professor);

        Professor response = professorService.createProfessor(professorDTO);

        assertNotNull(response);
        assertEquals(Professor.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Aderbal", response.getNome());
        assertEquals("Matematica", response.getArea());
        assertEquals(68, response.getIdade());
    }

    private void startProfessor() {
        professor = new Professor(1L, "Aderbal", 68, "Matematica", new ArrayList<>(), new ArrayList<>());
        professorDTO = new ProfessorDTO("Aderbal", 68, "Matematica");
    }
}