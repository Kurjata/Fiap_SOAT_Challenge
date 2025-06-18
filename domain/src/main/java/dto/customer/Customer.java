package dto.customer;

import java.time.LocalDateTime;

public class Customer {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private String documentNumber;
  private String name;
  private String email;

  public Customer() {
  }

  public Customer(String id, LocalDateTime timestampCreatedDate, String documentNumber, String name, String email) {
    this.id = id;
    this.timestampCreatedDate = timestampCreatedDate;
    this.documentNumber = documentNumber;
    this.name = name;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getTimestampCreatedDate() {
    return timestampCreatedDate;
  }

  public void setTimestampCreatedDate(LocalDateTime timestampCreatedDate) {
    this.timestampCreatedDate = timestampCreatedDate;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
