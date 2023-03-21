package com.kakao.domain.error;

/**
 * 각 계층, 도메인 별 구현할 표준 에러 코드 API
 */
public interface ErrorCode {
     String getMessage();
     String getCode();
     int getStatus();
}
