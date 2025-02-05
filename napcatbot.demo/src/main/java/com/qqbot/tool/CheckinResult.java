package com.qqbot.tool;

import lombok.Data;

//签到结果类
@Data
public class CheckinResult {
    private Integer goldEarned;       // 本次获得金币
    private Integer pointsEarned;     // 本次获得积分
    private Integer totalCheckins;    // 累计签到天数
    private Integer continuousDays;   // 当前连续签到天数
    private Integer checkinRank;      // 今日签到排名
    private Integer pointsRank;       // 积分总排名
}