package com.spring.login.exception.custom;


import com.spring.login.annotation.RateLimited;
import com.spring.login.service.RateLimitingService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tools.jackson.databind.ObjectMapper;


import java.util.Map;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private final RateLimitingService rateLimitingService;
    private final ObjectMapper  objectMapper;

    public RateLimitingInterceptor(RateLimitingService rateLimitingService, ObjectMapper objectMapper) {
        this.rateLimitingService = rateLimitingService;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            RateLimited rateLimited = handlerMethod.getMethodAnnotation(RateLimited.class);

            if (rateLimited != null) {
                String ipAddress = request.getRemoteAddr();
                Bucket bucket = rateLimitingService.resolveBucket(ipAddress);

                if (!bucket.tryConsume(1)) {

                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    response.setContentType("application/json;charset=UTF-8");
                    Map<String, String> errorResponse = Map.of("message", rateLimited.message());

                    String json = objectMapper.writeValueAsString(errorResponse);
                    response.getWriter().write(json);

                    return false; // Bloqueia a requisição e não deixa chegar ao Controller
                }
            }
        }
        return true;
    }
}