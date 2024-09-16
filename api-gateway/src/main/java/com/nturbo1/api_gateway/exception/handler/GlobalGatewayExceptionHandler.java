package com.nturbo1.api_gateway.exception.handler;

import com.nturbo1.api_gateway.exception.util.ExceptionHttpStatusCodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class GlobalGatewayExceptionHandler implements ErrorWebExceptionHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        exchange.getResponse().setStatusCode(ExceptionHttpStatusCodeMapper.getHttpStatus(ex));
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        ErrorResponseBody responseBody = new ErrorResponseBody(
                LocalDateTime.now(),
                Objects.requireNonNull(exchange.getResponse().getStatusCode()).toString(),
                ex.getMessage(),
                exchange.getRequest().getPath().toString());

        byte[] bytes = responseBody.toString().getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}
