package com.fiap.soat.model.response.queue;

import com.fiap.soat.model.response.PageResponse;
import com.fiap.soat.model.response.order.OrderResponse;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class QueuePageResponse  extends PageResponse<OrderResponse> {}
