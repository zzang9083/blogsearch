package com.kakao.application.service.keyword;

import com.kakao.domain.service.keyword.KeywordService;

import java.util.concurrent.TimeUnit;

import com.kakao.domain.vo.Search;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LockingKeywordRecordService implements KeywordService {
    private static final int WAIT_TIME = 10;
    private static final int LEASE_TIME = 7;
    private final RedissonClient redissonClient;
    private final TransactionalRecordService recordService;

    @Value("${lock.key}")
    private String lockKey;

    public LockingKeywordRecordService(RedissonClient redissonClient, TransactionalRecordService recordService) {
        this.redissonClient = redissonClient;
        this.recordService = recordService;
    }


    /**
     *
     * 분산락에 대한 반응성을 고려하여 비동기로 처리, 별도 Transaction 처리를 위한 Service 호출
     */
    @Async
    @Override
    public void record(Search search) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean available = lock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS);

            // WAITE_TIME 내 Lock 획득한 경우
            if (available) {
                recordService.recording(search);
            }

        } catch (InterruptedException e) {
            log.error("cannot acquire lock");
            Thread.currentThread().interrupt();
        } finally {
            if (lock.isHeldByCurrentThread() && lock.isLocked()) {
                lock.unlock();
            }
        }

    }
}
