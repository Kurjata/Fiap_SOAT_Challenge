package com.fiap.soat.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Customer operations")
public class CustomerController {

    @GetMapping
    public Mono<Void> teste() {
        return Mono.empty();
    }
}
