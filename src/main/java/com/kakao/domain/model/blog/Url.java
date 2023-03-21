package com.kakao.domain.model.blog;

import com.kakao.domain.error.BusinessException;
import com.kakao.domain.error.DomainErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode
@Getter
public class Url {
    private final String url;
    public Url(String url) {
        validation(url);
        this.url = url;
    }

    private void validation(String url) {
        if (url == null) {
            log.error("url cannot be null");
            throw new BusinessException(DomainErrorCode.INVALID_URL_ERROR);
        }
    }
}
