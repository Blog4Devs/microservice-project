
package com.commons.dtos;

import java.time.Instant;
import java.util.List;

import com.commons.enums.MaintenanceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDto {
    private Long id;
    private Instant startTime;
    private Instant predictedEndTime;
    private Instant endTime;
    private String description;
    private MaintenanceStatus status;
    private List<OperationDto> operations;
    private boolean isPaid;
    private Instant updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OperationDto {
        private Long id;
        private String name;
        private String description;
        private Long price;
        private Instant updatedAt;
    }
}
