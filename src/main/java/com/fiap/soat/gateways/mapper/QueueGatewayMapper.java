package com.fiap.soat.gateways.mapper;


import com.fiap.soat.entities.document.queue.QueueDocument;
import com.fiap.soat.entities.dto.order.OrderDTO;
import com.fiap.soat.entities.dto.queue.QueueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface QueueGatewayMapper extends GatewayMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "items", source = "products")
    QueueDocument create(OrderDTO dto);

    @Mapping(target = "id", qualifiedByName = "toObjectId")
    QueueDocument toDocument(QueueDTO dto);

    @Mapping(target = "id", qualifiedByName = "toId")
    QueueDTO toDTO(QueueDocument document);
}
