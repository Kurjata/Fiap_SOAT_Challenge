package document.customer;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collection = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDocument {
  @Id private ObjectId id;

  @Field(name = "dataHoraCriacao")
  @Builder.Default
  private LocalDateTime timestampCreatedDate = LocalDateTime.now();

  @Field(name = "cpf")
  private String documentNumber;

  @Field(name = "nome")
  private String name;

  private String email;
}
