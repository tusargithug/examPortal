package com.examportal.examPortal.Generic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
@Setter
@Getter
@NoArgsConstructor
@ToString
public class GenericResponse {
    private com.examportal.examPortal.Generic.ResponseStatus status;

    private Object data;

    public GenericResponse(HttpStatus code, String message, String data) {
        this.status =new com.examportal.examPortal.Generic.ResponseStatus(code.value(), message);
        this.data = data;
    }

    public GenericResponse(HttpStatus code, String data) {
        this.status = new com.examportal.examPortal.Generic.ResponseStatus(code.value());
        this.data = data;
    }

    public GenericResponse(HttpStatus code, Object data) {
        this.status = new com.examportal.examPortal.Generic.ResponseStatus(code.value());
        this.data = data;

    }

    public GenericResponse(int code, String message) {
        this.status = new com.examportal.examPortal.Generic.ResponseStatus(code, message);
    }
}
