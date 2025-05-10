package com.fiap.soat.model.document.order;

import com.fiap.soat.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Document(collection = "pedido")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDocument {
  @Id private ObjectId id;

  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreatedDate = LocalDateTime.now();

  @Field(name = "cliente")
  private OrderCustomerDocument customer;

  @Field(name = "valorTotal")
  @Builder.Default
  private BigDecimal totalAmount = BigDecimal.ZERO;

  @Builder.Default private OrderStatus status = OrderStatus.CREATED;

  @Builder.Default
  @Field(name = "produtos")
  private List<OrderProductDocument> products = new ArrayList<>();
}
