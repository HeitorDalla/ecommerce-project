package br.edu.unicesumar.exception;

// Se extender de 'Exception' quer dizer que o erro é 'Checked Exception' (o erro é esperado e obrigado a usar o try/catch, se não, é erro de compilação)
// Se extender de 'RuntimeException', significa que é erro lógico de programação, e o desenvolvedor decide se trata ou não, mas quebra o programa em tempo de execução
public class BusinessException extends Exception {
    public BusinessException (String message) {
        super(message);
    }
}