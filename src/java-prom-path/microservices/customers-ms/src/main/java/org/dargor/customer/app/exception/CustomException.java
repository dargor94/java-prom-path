package org.dargor.customer.app.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private final String timestamp = LocalDateTime.now().toString();
    private String message;
    private int code;

    public CustomException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
