package com.fiap.soat.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private String documentNumber;
  private String name;
}
