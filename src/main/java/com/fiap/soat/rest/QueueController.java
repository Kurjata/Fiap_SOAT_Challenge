package com.fiap.soat.rest;

import com.fiap.soat.constants.ExceptionSwagger;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/queue")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Queue Controller", description = "Queue operations")
public class QueueController {
    //TODO: get by id
    //TODO: buscar o próximo da fila para o status
    //TODO: evoluir status pelo id
    //TODO: buscar os últimos pelo status ordernado pelo timestemp do status
    //TODO:
}
