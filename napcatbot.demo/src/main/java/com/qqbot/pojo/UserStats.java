package com.qqbot.pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserStats {
    private Long groupId;
    private Long userId;
    private Integer totalCheckins;
    private Integer continuousDays;
    private LocalDate lastCheckinDate;
}
