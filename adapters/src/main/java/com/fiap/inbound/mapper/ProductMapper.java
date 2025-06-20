package com.fiap.inbound.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


import java.util.List;

import request.product.ProductRequest;
import response.product.ProductPageResponse;
import response.product.ProductResponse;
import document.product.ProductDocument;
import dto.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = SPRING)
public interface ProductMapper extends EntityMapper {

  @Mapping(target = "id", qualifiedByName = "toId")
  Product toProduct(ProductDocument document);

  @Mapping(target = "id", qualifiedByName = "toObjectId")
  @Mapping(target = "timestampCreatedDate", ignore = true)
  ProductDocument toDocument(Product dto);

  Product toProduct(ProductRequest request);

  @Mapping(target = "id", source = "id")
  Product toProduct(ProductRequest request, String id);

  ProductResponse toResponse(Product dto);

  @Mapping(target = "page", source = "number")
  @Mapping(target = "hasNext", expression = "java(page.hasNext())")
  @Mapping(target = "last", expression = "java(page.isLast())")
  @Mapping(target = "items", source = "content", qualifiedByName = "toContent")
  ProductPageResponse toPageResponse(PageImpl<Product> page);

  @Named("toContent")
  default List<ProductResponse> toContent(List<Product> list) {
    return list.stream().map(this::toResponse).toList();
  }
}
