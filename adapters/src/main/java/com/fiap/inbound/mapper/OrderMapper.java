package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.request.order.OrderAddProductRequest;
import com.fiap.request.order.OrderCreateRequest;
import com.fiap.response.order.OrderPageResponse;
import com.fiap.response.order.OrderResponse;
import com.fiap.document.order.OrderDocument;
import com.fiap.dto.order.OrderAddProduct;
import com.fiap.dto.order.OrderCustomer;
import com.fiap.dto.order.Order;
import com.fiap.dto.order.OrderFilter;
import com.fiap.dto.order.OrderProduct;
import com.fiap.dto.product.Product;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface OrderMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  Order toOrder(OrderDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  @Mapping(target = "totalAmount", source = "totalAmount")
  OrderDocument toDocument(Order order);

  @Mapping(target = "customer", source = "documentNumber", qualifiedByName = "toCustomerDTO")
  Order toOrder(OrderCreateRequest request);

  @Mapping(target = "totalAmount", source = "totalAmount")
  OrderResponse toResponse(Order dto);

  OrderAddProduct toOrder(OrderAddProductRequest dto);

  OrderProduct toOrderProduct(Product dto);

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.DateUtil.toDateTime(finalDate))")
  OrderFilter toFilter(
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
