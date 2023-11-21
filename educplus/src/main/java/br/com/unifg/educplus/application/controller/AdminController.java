package br.com.unifg.educplus.application.controller;

import br.com.unifg.educplus.domain.dto.CursoDTO;
import br.com.unifg.educplus.domain.service.CursoService;
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

    @PostMapping
    public String hello(){
        return "Hello Admin";
    }

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
}
