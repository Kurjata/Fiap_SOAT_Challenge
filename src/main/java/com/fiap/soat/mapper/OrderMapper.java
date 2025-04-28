package com.fiap.soat.mapper;

import com.fiap.soat.model.document.order.OrderDocument;
import com.fiap.soat.model.dto.order.OrderDTO;
import com.fiap.soat.model.request.order.OrderCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderMapper extends EntityMapper {

    @Mapping(target = "id", qualifiedByName = "toId")
    OrderDTO toDTO(OrderDocument document);

    @Mapping(target = "id", qualifiedByName = "toObjectId")
    @Mapping(target = "timestampCreatedDate", ignore = true)
    OrderDocument toDocument(OrderDTO dto);

    @Mapping(target = "customer.documentNumber", source = "documentNumber")
    OrderDTO toDTO(OrderCreateRequest request);
}
