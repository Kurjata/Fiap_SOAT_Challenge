package com.fiap.soat.presenters;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


import com.fiap.soat.entities.dto.charge.ChargeDTO;
import com.fiap.soat.entities.request.charge.ChargeCreateRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface ChargePresenter {
  ChargeDTO toDTO(ChargeCreateRequest request);
}
