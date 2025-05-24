package com.fiap.soat.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.model.document.order.OrderDocument;
import com.fiap.soat.model.dto.order.OrderAddProductDTO;
import com.fiap.soat.model.dto.order.OrderCustomerDTO;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.order.OrderFilterDTO;
import com.fiap.soat.model.dto.order.OrderProductDTO;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.model.request.order.OrderAddProductRequest;
import com.fiap.soat.model.request.order.OrderCreateRequest;
import com.fiap.soat.model.response.order.OrderPageResponse;
import com.fiap.soat.model.response.order.OrderResponse;
import com.fiap.soat.model.response.product.ProductPageResponse;
import com.fiap.soat.model.response.product.ProductResponse;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = SPRING)
public interface OrderMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  OrderDTO toDTO(OrderDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderDocument toDocument(OrderDTO dto);

  @Mapping(target = "customer", source = "documentNumber", qualifiedByName = "toCustomerDTO")
  OrderDTO toDTO(OrderCreateRequest request);

  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderResponse toResponse(OrderDTO dto);

  OrderAddProductDTO toDTO(OrderAddProductRequest dto);

  OrderProductDTO toOrderProductDTO(ProductDTO dto);

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.soat.util.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.soat.util.DateUtil.toDateTime(finalDate))")
  OrderFilterDTO toFilter(
      Integer page,
      Integer size,
      String documentNumber,
      String startDate,
      String finalDate,
      String status);

  @Mapping(target = "page", source = "number")
  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "last", expression = "java(page.isLast())")
  @Mapping(target = "items", source = "content", qualifiedByName = "toContent")
  OrderPageResponse toPageResponse(PageImpl<OrderDTO> page);

  @Named("toCustomerDTO")
  default OrderCustomerDTO toCustomerDTO(String documentNumber) {
    if (StringUtils.isEmpty(documentNumber)) return null;
    return OrderCustomerDTO.builder().documentNumber(documentNumber).build();
  }

  @Named("toContent")
  default List<OrderResponse> toContent(List<OrderDTO> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
