package br.com.unifg.educplus.domain.service;

import br.com.unifg.educplus.application.controller.AdminController;
import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Prova;
import br.com.unifg.educplus.infra.repositories.CursoRepository;
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

    public static final Prova PROVA = new Prova();

    Curso curso;
    CursoDTO cursoDTO;

    Optional<Curso> optionalCurso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

    private void startCurso() {
        curso = new Curso(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true, new ArrayList<>(), new ArrayList<>(), PROVA, new ArrayList<>());
        cursoDTO = new CursoDTO(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true);
        optionalCurso = Optional.of(new Curso(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true, new ArrayList<>(), new ArrayList<>(), PROVA, new ArrayList<>()));
    }
}