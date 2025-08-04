package com.fiap.soat.presenters;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import java.util.List;

import com.fiap.soat.entities.dto.queue.QueueDTO;
import com.fiap.soat.entities.dto.queue.QueueFilterDTO;
import com.fiap.soat.entities.response.queue.QueuePageResponse;
import com.fiap.soat.entities.response.queue.QueueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface QueuePresenter {

  @Mapping(
      target = "startDate",
      expression = "java(com.fiap.soat.entities.util.DateUtil.toDateTime(startDate))")
  @Mapping(
      target = "finalDate",
      expression = "java(com.fiap.soat.entities.util.DateUtil.toDateTime(finalDate))")
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
