package com.fiap.soat.mapper;

import com.fiap.soat.model.document.customer.CustomerDocument;
import com.fiap.soat.model.dto.CustomerDTO;
import com.fiap.soat.model.request.customer.CustomerCreateRequest;
import com.fiap.soat.model.response.customer.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  CustomerDTO toDTO(CustomerDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  CustomerDocument toDocument(CustomerDTO dto);

  CustomerDTO toDTO(CustomerCreateRequest request);

  CustomerResponse toResponse(CustomerDTO dto);
}
