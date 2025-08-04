package com.fiap.soat.usecase;


import com.fiap.soat.entities.dto.FilterDTO;
import org.springframework.data.domain.PageImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class FilterUseCase<T> {
  protected abstract Mono<Long> countByFilter(FilterDTO filter);

  protected abstract Flux<T> findByFilter(FilterDTO filter);

  protected Mono<PageImpl<T>> findPage(FilterDTO filter) {
    return countByFilter(filter)
        .flatMap(
            total ->
                findByFilter(filter)
                    .collectList()
                    .map(list -> new PageImpl<>(list, filter.getPageable(), total)));
  }
}
