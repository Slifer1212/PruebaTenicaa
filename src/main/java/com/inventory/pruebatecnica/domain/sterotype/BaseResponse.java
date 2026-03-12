package com.inventory.pruebatecnica.domain.sterotype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseResponse(BaseEntity entity) {
        if (Objects.nonNull(entity)) {
            this.id = entity.getId();
            this.createdAt = entity.getCreatedAt();
            this.updatedAt = entity.getUpdatedAt();
        }
    }

}
