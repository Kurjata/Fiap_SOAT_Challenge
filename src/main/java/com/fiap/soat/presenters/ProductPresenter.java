package com.fiap.soat.presenters;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.fiap.soat.entities.dto.product.ProductDTO;
import com.fiap.soat.entities.request.product.ProductRequest;
import java.util.List;

import com.fiap.soat.entities.response.product.ProductPageResponse;
import com.fiap.soat.entities.response.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface ProductPresenter {

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
