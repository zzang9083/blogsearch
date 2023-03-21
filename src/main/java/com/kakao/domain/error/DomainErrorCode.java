package com.kakao.domain.error;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DomainErrorCode implements ErrorCode {

    // KEYWORD domain
    INVALID_KEYWORD_ERROR(8010, "K001", " INVALID KEYWORD ERROR"),
    CANNOT_SEARCH_NULL_ERROR(8011, "K002", " SEARCH KEYWORD CANNOT BE NULL"),
    INVALID_KEYWORDS_ERROR(8012,"K003" ," INVALID KEYWORDS ERROR" ),

    // BLOG DOMAIN
    INVALID_BLOG_DATA_ERROR(9010, "B000", " INVALID BLOG DATA ERROR"),
    INVALID_BLOG_ERROR(9011, "B001", " INVALID BLOG ERROR"),
    INVALID_TITLE_ERROR(9012, "B002", " INVALID TITLE ERROR"),
    INVALID_CONTENTS_ERROR(9013, "B003", " INVALID CONTENTS ERROR"),
    INVALID_URL_ERROR(9014, "B004", " INVALID URL ERROR"),
    ;


    private final String code;
    private final String message;
    private int status;

    DomainErrorCode(final int status, final String code, final String message) {
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
