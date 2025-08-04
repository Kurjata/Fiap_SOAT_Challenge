package com.fiap.soat.presenters;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.entities.dto.order.OrderAddProductDTO;
import com.fiap.soat.entities.dto.order.OrderCustomerDTO;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.order.OrderFilterDTO;
import com.fiap.soat.entities.request.order.OrderAddProductRequest;
import com.fiap.soat.entities.request.order.OrderCreateRequest;
import com.fiap.soat.entities.response.order.OrderPageResponse;
import com.fiap.soat.entities.response.order.OrderResponse;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface OrderPresenter {
  @Mapping(target = "customer", source = "documentNumber", qualifiedByName = "toCustomerDTO")
  OrderDTO toDTO(OrderCreateRequest request);

  @Mapping(target = "totalAmount", expression = "java(dto.getTotalAmount())")
  OrderResponse toResponse(OrderDTO dto);

  OrderAddProductDTO toDTO(OrderAddProductRequest dto);

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.soat.entities.util.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.soat.entities.util.DateUtil.toDateTime(finalDate))")
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
