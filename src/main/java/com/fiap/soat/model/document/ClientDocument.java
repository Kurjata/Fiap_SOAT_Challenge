package com.fiap.soat.model.document;

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
@Document(collation = "cliente")
@NoArgsConstructor
@AllArgsConstructor
public class ClientDocument {
    @Id
    private ObjectId id;

    @Field(name = "nome")
    private String name;
}
