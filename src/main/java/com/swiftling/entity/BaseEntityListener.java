package com.swiftling.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BaseEntityListener {

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity) {

        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity) {
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
    }

}
