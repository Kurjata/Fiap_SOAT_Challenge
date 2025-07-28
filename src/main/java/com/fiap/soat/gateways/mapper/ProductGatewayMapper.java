package com.fiap.soat.gateways.mapper;


import com.fiap.soat.entities.document.product.ProductDocument;
import com.fiap.soat.entities.dto.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductGatewayMapper extends GatewayMapper {
  @Mapping(target = "id", qualifiedByName = "toId")
  ProductDTO toDTO(ProductDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  ProductDocument toDocument(ProductDTO dto);
}
