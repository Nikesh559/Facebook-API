package com.facebook.api.interceptor;

import com.facebook.api.exceptionhandling.ErrorMessage;
import com.facebook.api.service.MemberService;
import com.google.gson.Gson;
import io.github.bucket4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class RateLimitInterceptor implements HandlerInterceptor {

    private static HashMap<String, Bucket> cache = new HashMap<>();

    @Autowired
    private Gson gson;

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String apiKey = request.getHeader("X-api-key");
        apiKey = apiKey == null ? request.getParameter("apikey") : apiKey;
        Bucket bucket = getBucket(apiKey);

        if(bucket != null) {

            ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

            if(probe.isConsumed()) {
                response.setHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
                return true;
            }
            else {
                response.setHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(probe.getNanosToWaitForRefill()/1000000000));
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write(gson.toJson(new ErrorMessage(HttpStatus.TOO_MANY_REQUESTS, "You exhausted your API Request Quota")));
                return false;
            }
        }
        else {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(new ErrorMessage(HttpStatus.BAD_REQUEST, "Please provide valid API Key")));
            return false;
        }

    }



    public Bucket getBucket(String apiKey) {
        if(cache.containsKey(apiKey))
            return cache.get(apiKey);

        if(apiKey==null || !memberService.containsKey(apiKey))
            return null;

        Refill refill = Refill.intervally(5, Duration.ofSeconds(10));
        Bandwidth limit = Bandwidth.classic(5, refill);
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        cache.put(apiKey, bucket);
        return bucket;
    }
}
