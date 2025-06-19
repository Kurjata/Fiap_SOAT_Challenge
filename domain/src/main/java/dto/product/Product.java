package dto.product;

import enums.ProductCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
  private String id;
  private LocalDateTime timestampCreatedDate;
  private ProductCategory category;
  private BigDecimal amount;
  private String name;
  private String description;

  public Product() {}

  public Product(
      String id,
      LocalDateTime timestampCreatedDate,
      ProductCategory category,
      BigDecimal amount,
      String name,
      String description) {
    this.id = id;
    this.timestampCreatedDate = timestampCreatedDate;
    this.category = category;
    this.amount = amount;
    this.name = name;
    this.description = description;
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

  public ProductCategory getCategory() {
    return category;
  }

  public void setCategory(ProductCategory category) {
    this.category = category;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
