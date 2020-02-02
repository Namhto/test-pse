package com.namhto.mowitnow;

public class MowItNowException extends RuntimeException {

    private ErrorCode code;

    public MowItNowException(ErrorCode code) {
        super(code.toString());
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
