package br.com.unifg.educplus.application.controller;

import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.entity.Curso;
import br.com.unifg.educplus.domain.entity.Prova;
import br.com.unifg.educplus.domain.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class AdminControllerTest {

    @Mock
    private CursoService cursoService;
    @InjectMocks
    private AdminController adminController;

    public static final Prova PROVA = new Prova();

    Curso curso;
    CursoDTO cursoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(cursoService.create(any())).thenReturn(curso);

        ResponseEntity<CursoDTO> response = adminController.create(cursoDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(cursoService.update(cursoDTO)).thenReturn(curso);

        ResponseEntity<String> response = adminController.update(1L, cursoDTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
    }


    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(cursoService).delete((anyLong()));

        ResponseEntity<CursoDTO> response = adminController.delete(1L);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(adminController, times(1)).delete(anyLong());

    }


    private void startCurso() {
        curso = new Curso(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true, new ArrayList<>(), new ArrayList<>(), PROVA, new ArrayList<>());
        cursoDTO = new CursoDTO(1L, "Matematica", "Curso de matematica", 10.0, true, 5, true);
    }
}