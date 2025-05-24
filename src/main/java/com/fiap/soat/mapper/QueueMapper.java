package com.fiap.soat.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.dto.queue.QueueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public interface QueueMapper extends EntityMapper {

    @Mapping(target = "id", qualifiedByName = "toObjectId")
    @Mapping(target = "timestampCreatedDate", ignore = true)
    QueueDocument toDocument(QueueDTO dto);

    @Mapping(target = "id", qualifiedByName = "toId")
    QueueDTO toDTO(QueueDocument document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestampCreatedDate", ignore = true)
    @Mapping(target = "surname", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "items", source = "products")
    QueueDocument create(OrderDTO dto);
}
