package response.queue;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.fiap.soat.constants.Constants.DATE_TIME_PATTERN;
import static com.fiap.soat.constants.Description.QUEUE_ID_DESCRIPTION;
import static com.fiap.soat.constants.Description.QUEUE_ITEMS_DESCRIPTION;
import static com.fiap.soat.constants.Description.QUEUE_STATUS_DESCRIPTION;
import static com.fiap.soat.constants.Description.QUEUE_NICKNAME_DESCRIPTION;
import static com.fiap.soat.constants.Description.QUEUE_TIMESTAMP_CURRENT_STATUS_END_DESCRIPTION;
import static com.fiap.soat.constants.Example.DATE_TIME_EXAMPLE;
import static com.fiap.soat.constants.Example.ID_EXAMPLE;
import static com.fiap.soat.constants.Example.QUEUE_NICKNAME_EXAMPLE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueResponse {
    @Schema(description = QUEUE_ID_DESCRIPTION, example = ID_EXAMPLE)
    private String id;

    @Schema(description = QUEUE_STATUS_DESCRIPTION, implementation = QueueTrackingStatus.class)
    private QueueTrackingStatus status;

    @Schema(description = QUEUE_TIMESTAMP_CURRENT_STATUS_END_DESCRIPTION, example = DATE_TIME_EXAMPLE)
    @JsonFormat(pattern = DATE_TIME_PATTERN)
    private LocalDateTime timestampCurrentStatus;

    @Schema(description = QUEUE_NICKNAME_DESCRIPTION, example = QUEUE_NICKNAME_EXAMPLE)
    private String nickname;

    @Schema(description = QUEUE_ITEMS_DESCRIPTION)
    private List<QueueOrderItemsResponse> items;
}
