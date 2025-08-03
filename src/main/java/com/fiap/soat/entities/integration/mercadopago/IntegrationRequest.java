package com.fiap.soat.entities.integration.mercadopago;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationRequest<T> {
  @Builder.Default private Map<String, Object> pathVariables = new HashMap<>();

  @Builder.Default
  private LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

  @Builder.Default private Map<String, Object> queryParams = new HashMap<>();
  private T body;
}
