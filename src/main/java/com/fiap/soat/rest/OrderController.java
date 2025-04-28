package com.fiap.soat.rest;

import com.fiap.soat.constants.ExceptionSwagger;
import com.fiap.soat.mapper.OrderMapper;
import com.fiap.soat.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Order Controller", description = "Order operations")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    //TODO: Criar pedido

    //TODO: Adicionar produto

    //TODO: Remover produto

    //TODO: Cancelar pedido

    //TODO: Criar chamada para criar pagamento (no primeiro momento só muda status pois não tem regra de negócio)

    //TODO: listar pedidos

}
