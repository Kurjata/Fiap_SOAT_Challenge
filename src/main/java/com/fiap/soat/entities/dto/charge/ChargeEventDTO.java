package com.fiap.soat.entities.dto.charge;

import com.fiap.soat.entities.enums.ChargeEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeEventDTO {
    private ChargeEventType type;
    
}
