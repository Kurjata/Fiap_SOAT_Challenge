package com.fiap.soat.usecase;

import reactor.core.publisher.Mono;

public interface UseCase<T,R> {
    Mono<R> execute(T obj);
}
