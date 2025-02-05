package com.qqbot.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

//签到记录实体类
@Data
public class CheckinRecord {
    private Long recordId;
    private Long groupId;
    private Long userId;
    private LocalDate checkinDate;
    private LocalDateTime checkinTime;
    private Integer goldEarned;
    private Integer pointsEarned;
}