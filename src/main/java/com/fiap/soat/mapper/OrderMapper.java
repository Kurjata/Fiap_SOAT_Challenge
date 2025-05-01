package com.fiap.soat.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.model.document.order.OrderDocument;
import com.fiap.soat.model.dto.order.OrderAddProductDTO;
import com.fiap.soat.model.dto.order.OrderCustomerDTO;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.order.OrderProductDTO;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.model.request.order.OrderAddProductRequest;
import com.fiap.soat.model.request.order.OrderCreateRequest;
import com.fiap.soat.model.response.order.OrderResponse;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = SPRING)
public interface OrderMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  OrderDTO toDTO(OrderDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  @Mapping(target = "totalAmount", ignore = true)
  @Mapping(target = "status", ignore = true)
  OrderDocument createToDocument(OrderDTO dto);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  OrderDocument toDocument(OrderDTO dto);

  @Mapping(target = "customer", source = "documentNumber", qualifiedByName = "toCustomerDTO")
  OrderDTO toDTO(OrderCreateRequest request);

  OrderResponse toResponse(OrderDTO dto);

  OrderAddProductDTO toDTO(OrderAddProductRequest dto);

  OrderProductDTO toOrderProductDTO(ProductDTO dto);

  @Named("toCustomerDTO")
  default OrderCustomerDTO toCustomerDTO(String documentNumber) {
    if (StringUtils.isEmpty(documentNumber)) return null;
    return OrderCustomerDTO.builder().documentNumber(documentNumber).build();
  }
}
