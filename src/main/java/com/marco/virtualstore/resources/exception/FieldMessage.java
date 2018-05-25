package com.marco.virtualstore.resources.exception;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Marco Antônio on 25/05/2018
 */
public class FieldMessage implements Serializable {

    private static final long serialVersionUID = -3425861712485786917L;

    private String fieldName;

    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
