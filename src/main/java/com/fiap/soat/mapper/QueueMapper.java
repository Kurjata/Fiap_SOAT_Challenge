package com.fiap.soat.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.model.document.queue.QueueDocument;
import com.fiap.soat.model.dto.queue.QueueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public interface QueueMapper extends EntityMapper {

    @Mapping(target = "id", qualifiedByName = "toObjectId")
    @Mapping(target = "timestampCreatedDate", ignore = true)
    @Mapping(target = "surname", ignore = true)
    QueueDocument toDocument(QueueDTO dto);

    @Mapping(target = "id", qualifiedByName = "toId")
    QueueDTO toDTO(QueueDocument document);
}
