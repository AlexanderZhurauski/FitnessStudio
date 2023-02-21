package org.mycompany.fitness.core.exceptions.errors;

import java.util.List;

public class MultipleErrorResponse {

    private String logref;
    private List<ErrorField> errors;

    public MultipleErrorResponse(String logref, List<ErrorField> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<ErrorField> getErrors() {
        return errors;
    }
}
