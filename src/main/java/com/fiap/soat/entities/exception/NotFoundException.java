package com.fiap.soat.entities.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.fiap.soat.entities.enums.ServiceError;

public class NotFoundException extends ChallengeException {
  public NotFoundException(ServiceError error) {
    super(error.name(), NOT_FOUND);
  }
}
