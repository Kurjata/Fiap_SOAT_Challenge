package com.fiap.soat.presenters;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


import com.fiap.soat.entities.dto.customer.CustomerDTO;
import com.fiap.soat.entities.request.customer.CustomerCreateRequest;
import com.fiap.soat.entities.response.customer.CustomerResponse;
import com.fiap.soat.entities.util.CustomerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = SPRING)
public interface CustomerPresenter {

  @Mapping(target = "documentNumber", qualifiedByName = "clearDocumentNumber")
  CustomerDTO toDTO(CustomerCreateRequest request);

  CustomerResponse toResponse(CustomerDTO dto);

  @Named("clearDocumentNumber")
  default String clearDocumentNumber(String documentNumber) {
    return CustomerUtil.clearDocumentNumber(documentNumber);
  }
}
