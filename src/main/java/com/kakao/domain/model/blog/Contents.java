package com.kakao.domain.model.blog;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
@Getter
public class Contents {

    private final String contents;
    public Contents(String contents) {
        validation(contents);
        this.contents = contents;
    }

    private void validation(String contents) {
        if (contents == null) {
            log.error("contents cannot be null");
            throw new BusinessException(DomainErrorCode.INVALID_CONTENTS_ERROR);
        }
    }
}
