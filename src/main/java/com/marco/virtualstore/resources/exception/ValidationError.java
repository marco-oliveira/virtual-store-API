package com.marco.virtualstore.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco Antônio on 25/05/2018
 */
public class ValidationError extends StandardError {

    private static final long serialVersionUID = -5078666141229573157L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long timeStamp) {
        super(status, message, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
