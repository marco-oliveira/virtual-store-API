package com.marco.virtualstore.services.exceptions;

import java.io.Serializable;

/**
 * Created by Marco Antônio on 18/05/2018
 */
public class ObjectNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 768475434395533492L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
