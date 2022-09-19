package bri.ifsp.edu.br.patrimonioapi.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DefaultException extends Exception {

    public DefaultException() {
        super();
    }

    public DefaultException(String message) {
        super(message);
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(String message, String cause) {
        super(message, new Throwable(cause));
    }

    public DefaultException(Throwable cause) {
        super(cause);
    }
}
