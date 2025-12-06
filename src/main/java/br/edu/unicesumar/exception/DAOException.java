package br.edu.unicesumar.exception;

public class DAOException extends RuntimeException { // ele deve estender de 'RuntimeException' devido que erro de banco é erro tecnico de infraestrutura (nao é esperado e nao deve aparecer para o usuario)
    public DAOException (String message) {
        super(message);
    }
}