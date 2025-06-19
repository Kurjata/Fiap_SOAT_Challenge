package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import request.order.OrderAddProductRequest;
import request.order.OrderCreateRequest;
import response.order.OrderPageResponse;
import response.order.OrderResponse;
import com.fiap.outbound.model.order.OrderDocument;
import dto.order.OrderAddProduct;
import dto.order.OrderCustomer;
import dto.order.Order;
import dto.order.OrderFilterDTO;
import dto.order.OrderProductDTO;
import dto.product.Product;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface OrderMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  Order toDTO(OrderDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderDocument toDocument(Order dto);

  @Mapping(target = "customer", source = "documentNumber", qualifiedByName = "toCustomerDTO")
  Order toDTO(OrderCreateRequest request);

  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderResponse toResponse(Order dto);

  OrderAddProduct toDTO(OrderAddProductRequest dto);

  OrderProductDTO toOrderProductDTO(Product dto);

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
  OrderPageResponse toPageResponse(PageImpl<Order> page);

  @Named("toCustomerDTO")
  default OrderCustomer toCustomerDTO(String documentNumber) {
    if (StringUtils.isEmpty(documentNumber)) return null;
    return OrderCustomer.builder().documentNumber(documentNumber).build();
  }

  @Named("toContent")
  default List<OrderResponse> toContent(List<Order> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
