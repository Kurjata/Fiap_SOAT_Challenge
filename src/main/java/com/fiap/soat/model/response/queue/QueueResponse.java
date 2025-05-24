package com.fiap.soat.model.response.queue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.soat.model.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.fiap.soat.constants.Constants.DATE_TIME_PATTERN;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueResponse {
    private String id;
    private QueueTrackingStatus status;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime timestampCurrentStatus;

    private String surname;
    private List<QueueOrderItemsResponse> items;
}
