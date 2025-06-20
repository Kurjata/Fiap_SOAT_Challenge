package com.fiap.service;

import com.fiap.dto.charge.Charge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ChargeService {
  Mono<Charge> create(Charge charge);

  Mono<Charge> payment(Charge charge);
}
