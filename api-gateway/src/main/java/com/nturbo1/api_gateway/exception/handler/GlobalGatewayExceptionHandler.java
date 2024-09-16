package com.nturbo1.api_gateway.exception.handler;

import com.nturbo1.api_gateway.exception.util.ExceptionHttpStatusCodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GlobalGatewayExceptionHandler implements ErrorWebExceptionHandler {


    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        exchange.getResponse().setStatusCode(ExceptionHttpStatusCodeMapper.getHttpStatus(ex));
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String errorMessage = "{\"error\": \"" + ex.getMessage() + "\"}";

        byte[] bytes = errorMessage.getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }
}
