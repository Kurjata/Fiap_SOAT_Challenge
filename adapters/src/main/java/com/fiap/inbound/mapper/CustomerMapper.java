package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.inbound.model.request.customer.CustomerCreateRequest;
import com.fiap.inbound.model.response.customer.CustomerResponse;
import com.fiap.outbound.model.customer.CustomerDocument;
import dto.customer.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import util.CustomerUtil;

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
