package com.marco.virtualstore.services.exceptions;

import java.io.Serializable;

/**
 * Created by Marco Antônio on 22/05/2018
 */
public class DataIntegrityException extends RuntimeException{

    private static final long serialVersionUID = -3721483667988617548L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
