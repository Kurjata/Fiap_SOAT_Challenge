package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.inbound.model.response.queue.QueuePageResponse;
import com.fiap.inbound.model.response.queue.QueueResponse;
import com.fiap.outbound.model.queue.QueueDocument;
import dto.order.OrderDTO;
import dto.queue.QueueDTO;
import dto.queue.QueueFilterDTO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface QueueMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  QueueDocument toDocument(QueueDTO dto);

  @Mapping(target = "id", qualifiedByName = "toId")
  QueueDTO toDTO(QueueDocument document);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "nickname", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "orderId", source = "id")
  @Mapping(target = "items", source = "products")
  QueueDocument create(OrderDTO dto);

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.soat.util.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.soat.util.DateUtil.toDateTime(finalDate))")
  QueueFilterDTO toFilter(
      Integer page, Integer size, String startDate, String finalDate, String status);

  QueueResponse toResponse(QueueDTO dto);

  @Mapping(target = "page", source = "number")
  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "last", expression = "java(page.isLast())")
  @Mapping(target = "items", source = "content", qualifiedByName = "toContent")
  QueuePageResponse toPageResponse(PageImpl<QueueDTO> page);

  @Named("toContent")
  default List<QueueResponse> toContent(List<QueueDTO> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
