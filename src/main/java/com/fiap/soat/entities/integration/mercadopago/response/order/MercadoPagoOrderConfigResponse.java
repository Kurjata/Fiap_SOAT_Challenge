package com.fiap.soat.entities.integration.mercadopago.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoOrderConfigResponse {
    private MercadoPagoOrderConfigQrResponse qr;
}
