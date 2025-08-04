package com.fiap.soat.gateways.mapper;


import com.fiap.soat.entities.document.order.OrderDocument;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.order.OrderProductDTO;
import com.fiap.soat.entities.dto.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderGatewayMapper extends GatewayMapper {
  @Mapping(target = "id", qualifiedByName = "toId")
  OrderDTO toDTO(OrderDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderDocument toDocument(OrderDTO dto);

  OrderProductDTO toOrderProductDTO(ProductDTO dto);
}
