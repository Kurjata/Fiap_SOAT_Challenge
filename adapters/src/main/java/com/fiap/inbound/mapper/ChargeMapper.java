package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.inbound.model.request.charge.ChargeCreateRequest;
import dto.charge.ChargeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface ChargeMapper extends EntityMapper {
  ChargeDTO toDTO(ChargeCreateRequest request);
}
