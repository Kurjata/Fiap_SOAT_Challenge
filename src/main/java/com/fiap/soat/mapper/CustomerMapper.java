package com.fiap.soat.mapper;

import com.fiap.soat.model.document.customer.CustomerDocument;
import com.fiap.soat.model.dto.customer.CustomerDTO;
import com.fiap.soat.model.request.customer.CustomerCreateRequest;
import com.fiap.soat.model.response.customer.CustomerResponse;
import com.fiap.soat.util.CustomerUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  CustomerDTO toDTO(CustomerDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  CustomerDocument toDocument(CustomerDTO dto);

  @Mapping(target = "documentNumber", qualifiedByName = "clearDocumentNumber")
  CustomerDTO toDTO(CustomerCreateRequest request);

  CustomerResponse toResponse(CustomerDTO dto);

  @Named("clearDocumentNumber")
  default String clearDocumentNumber(String documentNumber) {
    return CustomerUtil.clearDocumentNumber(documentNumber);
  }
}
