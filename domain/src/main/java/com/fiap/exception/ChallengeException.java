package com.fiap.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class ChallengeException extends RuntimeException {
  private final String message;
  private final HttpStatus status;
}
