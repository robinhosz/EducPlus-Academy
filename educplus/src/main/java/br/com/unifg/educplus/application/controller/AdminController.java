package br.com.unifg.educplus.application.controller;

import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.dto.ProfessorDTO;
import br.com.unifg.educplus.domain.dto.UserDTO;
import br.com.unifg.educplus.domain.service.CursoService;
import br.com.unifg.educplus.domain.service.ProfessorService;
import br.com.unifg.educplus.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfessorService professorService;

    @PostMapping(value = "/curso")
    public ResponseEntity<CursoDTO> create(@RequestBody CursoDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cursoService.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/curso/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CursoDTO obj) {
        obj.setId(id);
        cursoService.update(obj);
        return ResponseEntity.ok().body("Curso atualizado com sucesso");
    }

    @DeleteMapping(value = "/curso/{id}")
    public ResponseEntity<CursoDTO> delete(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userService.createUser(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "/users/professor/create")
    public ResponseEntity<ProfessorDTO> createProfessor(@RequestBody ProfessorDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(professorService.createProfessor(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping(value = "/curso/{idCurso}/professor/{idProfessor}/add")
    public ResponseEntity<String> addProfessor(@PathVariable Long idCurso, @PathVariable Long idProfessor) {
        cursoService.addProfessor(idCurso, idProfessor);
        return ResponseEntity.ok().body("Professor adicionado com sucesso ao curso");
    }
}
