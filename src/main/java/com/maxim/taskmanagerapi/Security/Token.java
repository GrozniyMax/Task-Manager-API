package com.maxim.taskmanagerapi.Security;

import java.time.LocalDateTime;
import java.time.Period;

public record Token(Long id, LocalDateTime createdAt) {

    public boolean isExpired() {
        return LocalDateTime.now().getHour()-createdAt.getHour()>=2;
    }
}
