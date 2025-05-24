package com.fiap.soat.model.response.queue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.soat.model.enums.QueueTrackingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueResponse {
    private String id;
    private QueueTrackingStatus status;
    private LocalDateTime timestampCurrentStatus;
    private String surname;
    private List<QueueOrderItemsResponse> items;
}
