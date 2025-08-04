package com.fiap.soat.api.configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fiap.soat.entities.response.exception.ExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponse(
    responseCode = "400",
    content =
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(
    responseCode = "404",
    content =
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ExceptionResponse.class)))
@ApiResponse(
    responseCode = "422",
    content =
        @Content(
            mediaType = APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ExceptionResponse.class)))
public @interface ExceptionSwagger {}
