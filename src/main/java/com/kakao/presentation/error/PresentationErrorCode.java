package com.kakao.presentation.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kakao.domain.error.ErrorCode;

/**
 * presentation 계층에서 발생하는 예외 및 에러를 처리하기 위한 에러 코드
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PresentationErrorCode implements ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "P001", " INVALID INPUT VALUE"),
    METHOD_NOT_ALLOWED(405, "P002", " NOT SUPPORT HTTP METHOD"),
    INTERNAL_SERVER_ERROR(500, "P004", "SERVER ERROR"),
    NO_CONTENT(204, "P005", "NO CONTENTS")
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
