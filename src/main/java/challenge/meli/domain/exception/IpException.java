package challenge.meli.domain.exception;

public class IpException extends RuntimeException {

    private int codeError;

    public IpException(String message, int codeError) {
        super(message);
        this.codeError = codeError;
    }
}
