package com.fiap.inbound.rest;


import static com.fiap.inbound.constants.Constants.PAGE_DEFAULT;
import static com.fiap.inbound.constants.Constants.SIZE_DEFAULT;
import static com.fiap.inbound.constants.ControllerExceptions.PRODUCT_CREATE_CATEGORY_INVALID;
import static com.fiap.inbound.constants.Description.PAGE_PARAMETER_DESCRIPTION;
import static com.fiap.inbound.constants.Description.PAGE_PARAMETER_SIZE_DESCRIPTION;
import static com.fiap.inbound.constants.Description.PRODUCT_CATEGORY_DESCRIPTION;
import static com.fiap.inbound.constants.Description.PRODUCT_ID_DESCRIPTION;
import static com.fiap.inbound.constants.Example.ID_EXAMPLE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import com.fiap.inbound.constants.ExceptionSwagger;
import com.fiap.inbound.model.request.product.ProductRequest;
import com.fiap.inbound.model.response.product.ProductPageResponse;
import com.fiap.inbound.model.response.product.ProductResponse;
import com.fiap.inbound.rest.validation.ValueOfEnum;
import enums.ProductCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@Validated
@RequiredArgsConstructor
@ExceptionSwagger
@Tag(name = "Product Controller", description = "Product operations")
public class ProductController {
  private final ProductMapper productMapper;
  private final ProductService productService;

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(
      summary = "Product creation",
      description = "This endpoint is used to create a new product in the database.",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Product created",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ProductResponse.class))))
  public Mono<ProductResponse> create(@RequestBody @Valid final ProductRequest request) {
    return Mono.just(request)
        .map(productMapper::toDTO)
        .flatMap(productService::create)
        .map(productMapper::toResponse);
  }

  @PutMapping("/{id}")
  @ResponseStatus(OK)
  @Operation(
      summary = "Product update",
      description = "This endpoint is used to update a product in the database.",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Product updated",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ProductResponse.class))))
  public Mono<ProductResponse> update(
      @PathVariable @Parameter(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE) final String id,
      @RequestBody @Valid final ProductRequest request) {
    return Mono.just(request)
        .map(r -> productMapper.toDTO(r, id))
        .flatMap(productService::update)
        .map(productMapper::toResponse);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  @Operation(
      summary = "Product delete",
      description = "This endpoint is used to delete a product in the database.",
      responses =
          @ApiResponse(
              responseCode = "204",
              description = "Product deleted",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ProductResponse.class))))
  public Mono<Void> delete(
      @PathVariable @Parameter(description = PRODUCT_ID_DESCRIPTION, example = ID_EXAMPLE) final String id) {
    return productService.delete(id);
  }

  @GetMapping("/category/{category}")
  @ResponseStatus(OK)
  @Operation(
      summary = "Product get by category",
      description = "This endpoint is used to get products",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Products searched",
              content =
                  @Content(
                      mediaType = APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ProductPageResponse.class))))
  public Mono<ProductPageResponse> getByCategory(
      @PathVariable
          @Parameter(
              description = PRODUCT_CATEGORY_DESCRIPTION,
              schema = @Schema(implementation = ProductCategory.class))
          @Valid
          @ValueOfEnum(enumClass = ProductCategory.class, message = PRODUCT_CREATE_CATEGORY_INVALID)
          final String category,
      @RequestParam(required = false, defaultValue = PAGE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_DESCRIPTION)
          final Integer page,
      @RequestParam(required = false, defaultValue = SIZE_DEFAULT)
          @Parameter(description = PAGE_PARAMETER_SIZE_DESCRIPTION)
          final Integer size) {

    return Mono.just(category)
        .map(ProductCategory::getByName)
        .map(c -> Pair.of(c, PageRequest.of(page, size)))
        .flatMap(pair -> this.productService.getByCategory(pair.getLeft(), pair.getRight()))
        .map(this.productMapper::toPageResponse);
  }
}
