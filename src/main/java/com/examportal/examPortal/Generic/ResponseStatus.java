package com.examportal.examPortal.Generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStatus {
    private int code;

    private String msgCode;

    private String message;

    private long timestamp = System.currentTimeMillis();

    public ResponseStatus(int code, String message) {

        this.code = code;
        this.message = message;
    }

    public ResponseStatus(int code, String msgCode, String message) {

        this.code = code;
        this.msgCode = msgCode;
        this.message = message;
    }

    public ResponseStatus(int code) {
        this.code = code;
    }
}
