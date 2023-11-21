package br.com.unifg.educplus.domain.exceptions;

public class CursoNotFound extends RuntimeException {

    public CursoNotFound(String message) {
        super(message);
    }
}
