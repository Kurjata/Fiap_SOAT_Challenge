package com.fiap.soat.mapper;

import com.fiap.soat.model.document.product.ProductDocument;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.model.request.product.ProductCreateRequest;
import com.fiap.soat.model.response.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  ProductDTO toDTO(ProductDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  ProductDocument toDocument(ProductDTO dto);

  ProductDTO toDTO(ProductCreateRequest request);

  ProductResponse toResponse(ProductDTO dto);
}
