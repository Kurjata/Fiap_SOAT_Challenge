package com.fiap.soat.mapper;

import com.fiap.soat.model.document.product.ProductDocument;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.model.request.product.ProductRequest;
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

  ProductDTO toDTO(ProductRequest request);

  @Mapping(target = "id", source = "id")
  ProductDTO toDTO(ProductRequest request, String id);

  ProductResponse toResponse(ProductDTO dto);
}
