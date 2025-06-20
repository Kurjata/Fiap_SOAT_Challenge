package com.fiap.exception;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import com.fiap.enums.ServiceError;

public class BusinessException extends ChallengeException {
  public BusinessException(ServiceError error) {
    super(error.name(), UNPROCESSABLE_ENTITY);
  }
}
