package com.fiap.response;

import static com.fiap.constants.Description.PAGE_RESPONSE_HAS_NEXT_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_RESPONSE_IS_LAST_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_RESPONSE_ITEMS_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_RESPONSE_PAGE_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_RESPONSE_SIZE_DESCRIPTION;
import static com.fiap.constants.Description.PAGE_RESPONSE_TOTAL_PAGES_DESCRIPTION;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class PageResponse<T> {
    @Schema(description = PAGE_RESPONSE_PAGE_DESCRIPTION)
    private Integer page;

    @Schema(description = PAGE_RESPONSE_SIZE_DESCRIPTION)
    private Integer size;

    @Schema(description = PAGE_RESPONSE_TOTAL_PAGES_DESCRIPTION)
    private Integer totalPages;

    @Schema(description = PAGE_RESPONSE_IS_LAST_DESCRIPTION)
    private boolean last;

    @Schema(description = PAGE_RESPONSE_HAS_NEXT_DESCRIPTION)
    private boolean hasNext;

  @Schema(description = PAGE_RESPONSE_ITEMS_DESCRIPTION)
  @Builder.Default
  private List<T> items = new ArrayList<>();
}
