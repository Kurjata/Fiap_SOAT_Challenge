package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.request.charge.ChargeCreateRequest;
import com.fiap.dto.charge.Charge;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface ChargeMapper extends EntityMapper {
  Charge toDTO(ChargeCreateRequest request);
}
