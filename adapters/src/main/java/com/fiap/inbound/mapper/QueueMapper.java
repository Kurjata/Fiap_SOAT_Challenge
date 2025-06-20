package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.response.queue.QueuePageResponse;
import com.fiap.response.queue.QueueResponse;
import com.fiap.document.queue.QueueDocument;
import com.fiap.dto.order.Order;
import com.fiap.dto.queue.Queue;
import com.fiap.dto.queue.QueueFilter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface QueueMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  QueueDocument toDocument(Queue dto);

  @Mapping(target = "id", qualifiedByName = "toId")
  Queue toQueue(QueueDocument document);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "nickname", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "orderId", source = "id")
  @Mapping(target = "items", source = "products")
  QueueDocument create(Order dto);

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.DateUtil.toDateTime(finalDate))")
  QueueFilter toFilter(
      Integer page, Integer size, String startDate, String finalDate, String status);

  QueueResponse toResponse(Queue dto);

  @Mapping(target = "page", source = "number")
  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "last", expression = "java(page.isLast())")
  @Mapping(target = "items", source = "content", qualifiedByName = "toContent")
  QueuePageResponse toPageResponse(PageImpl<Queue> page);

  @Named("toContent")
  default List<QueueResponse> toContent(List<Queue> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
