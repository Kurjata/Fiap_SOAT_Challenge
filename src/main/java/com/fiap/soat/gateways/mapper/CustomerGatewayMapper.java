package com.fiap.soat.gateways.mapper;


import com.fiap.soat.entities.document.customer.CustomerDocument;
import com.fiap.soat.entities.dto.customer.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerGatewayMapper extends GatewayMapper {
  @Mapping(target = "id", qualifiedByName = "toId")
  CustomerDTO toDTO(CustomerDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  CustomerDocument toDocument(CustomerDTO dto);
}
