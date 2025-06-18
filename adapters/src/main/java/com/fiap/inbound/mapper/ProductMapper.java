package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.model.document.product.ProductDocument;
import com.fiap.soat.model.dto.product.ProductDTO;
import com.fiap.soat.model.request.product.ProductRequest;
import com.fiap.soat.model.response.product.ProductPageResponse;
import com.fiap.soat.model.response.product.ProductResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

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

  @Mapping(target = "page", source = "number")
  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "last", expression = "java(page.isLast())")
  @Mapping(target = "items", source = "content", qualifiedByName = "toContent")
  ProductPageResponse toPageResponse(PageImpl<ProductDTO> page);

  @Named("toContent")
  default List<ProductResponse> toContent(List<ProductDTO> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
