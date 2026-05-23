package com.spring.login.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {

    private final Map<String , Bucket> cache = new ConcurrentHashMap<>();

    private Bucket criarNewBucket(){
        Bandwidth limit = Bandwidth.classic(5 , Refill.intervally(5 , Duration.ofMinutes(5)));
        return Bucket.builder().addLimit(limit).build();
    }

    public Bucket resolveBucket(String ipAddress){
        return cache.computeIfAbsent(ipAddress, key -> criarNewBucket());
    }
}
