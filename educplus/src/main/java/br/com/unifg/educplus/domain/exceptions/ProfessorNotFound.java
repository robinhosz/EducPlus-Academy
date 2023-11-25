package br.com.unifg.educplus.domain.exceptions;

public class ProfessorNotFound extends RuntimeException {

    public ProfessorNotFound(String message) {
        super(message);
    }
}
