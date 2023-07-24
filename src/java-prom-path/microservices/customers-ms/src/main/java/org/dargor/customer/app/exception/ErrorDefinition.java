package org.dargor.customer.app.exception;

import lombok.Getter;

@Getter
public enum ErrorDefinition {

    INVALID_FIELDS("Please verify input data"),
    UNKNOWN_ERROR("Unknown error occurred"),
    ENTITY_NOT_FOUND("Entity not found"),
    PATH_NOT_FOUND("Path not found"),
    CONTENT_TYPE_NOT_SUPPORTED("Content type not supported")
    ;

    private final String message;

    ErrorDefinition(String message) {
        this.message = message;
    }
}
