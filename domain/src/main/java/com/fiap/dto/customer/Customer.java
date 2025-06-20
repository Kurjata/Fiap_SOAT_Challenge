package com.fiap.dto.customer;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private String documentNumber;
  private String name;
  private String email;
}
