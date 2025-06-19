package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import request.charge.ChargeCreateRequest;
import dto.charge.Charge;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface ChargeMapper extends EntityMapper {
  Charge toDTO(ChargeCreateRequest request);
}
