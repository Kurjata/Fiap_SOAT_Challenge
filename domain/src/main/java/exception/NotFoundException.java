package exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import enums.ServiceError;

public class NotFoundException extends ChallengeException {
  public NotFoundException(ServiceError error) {
    super(error.name(), NOT_FOUND);
  }
}
