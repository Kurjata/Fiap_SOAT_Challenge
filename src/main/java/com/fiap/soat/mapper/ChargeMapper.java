package com.fiap.soat.mapper;

import com.fiap.soat.model.dto.charge.ChargeDTO;
import com.fiap.soat.model.request.charge.ChargeCreateRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChargeMapper extends EntityMapper {
  ChargeDTO toDTO(ChargeCreateRequest request);
}
