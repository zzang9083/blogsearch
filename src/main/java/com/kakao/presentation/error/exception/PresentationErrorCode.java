package com.kakao.presentation.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.domain.error.ErrorCode;

/**
 * presentation 계층에서 발생하는 예외 및 에러를 처리하기 위한 에러 코드
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PresentationErrorCode implements ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " INVALID INPUT VALUE"),
    METHOD_NOT_ALLOWED(405, "C002", " NOT SUPPORT HTTP METHOD"),
    INTERNAL_SERVER_ERROR(500, "C003", "SERVER ERROR"),
    NO_CONTENT(204, "C004", "NO CONTENTS")
    ;
    private final String code;
    private final String message;
    private int status;

    PresentationErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
