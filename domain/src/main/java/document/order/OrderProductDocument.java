package document.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDocument {
  private String id;

  @Field(name = "valor")
  private BigDecimal amount;

  @Field(name = "quantidade")
  private Integer quantity;

  @Field(name = "nome")
  private String name;

  @Field(name = "descricao")
  private String description;
}
