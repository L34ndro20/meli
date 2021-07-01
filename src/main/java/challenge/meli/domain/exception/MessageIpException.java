package challenge.meli.domain.exception;

public enum MessageIpException {
    INVALID_IP("La ip escrita no es válida", 400),
    NO_FOUND_DATA_IP("No se encontro datos con la ip", 400),
    IP_LIST_BLACK("la ip se encuentra bloqueada", 400),
    NO_FOUND_DATA_PAIS("No se encontro datos para el país", 400);

    private String message;
    private int code;

    MessageIpException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public IpException get(){
        return new IpException(this.message, this.code);
    }
}
