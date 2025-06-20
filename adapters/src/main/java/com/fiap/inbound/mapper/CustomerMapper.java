package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import request.customer.CustomerCreateRequest;
import response.customer.CustomerResponse;
import document.customer.CustomerDocument;
import dto.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import util.CustomerUtil;

@Mapper(componentModel = SPRING)
public interface CustomerMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  Customer toCustomer(CustomerDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  CustomerDocument toDocument(Customer dto);

  @Mapping(target = "documentNumber", qualifiedByName = "clearDocumentNumber")
  Customer toCustomer(CustomerCreateRequest request);

  CustomerResponse toResponse(Customer dto);

  @Named("clearDocumentNumber")
  default String clearDocumentNumber(String documentNumber) {
    return CustomerUtil.clearDocumentNumber(documentNumber);
  }
}
