package com.qqbot.service;


import com.qqbot.mapper.CheckinRecordMapper;
import com.qqbot.mapper.GroupMemberMapper;
import com.qqbot.mapper.UserStatsMapper;
import com.qqbot.pojo.CheckinRecord;
import com.qqbot.pojo.UserStats;
import com.qqbot.tool.CheckinResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

//签到实现类
@Service
public class CheckinService {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Autowired
    private CheckinRecordMapper checkinRecordMapper;

    @Autowired
    private UserStatsMapper userStatsMapper;

    public CheckinResult checkin(Long groupId,Long userId){
        //检查是否已签到
        LocalDate today = LocalDate.now();

        //随机生成金币
        int gold = ThreadLocalRandom.current().nextInt(100,1001);
        int points = ThreadLocalRandom.current().nextInt(10,101);
        //更新用户积分和金币
        groupMemberMapper.addGoldAndPoints(groupId,userId,gold,points);

        //插入签到记录
        CheckinRecord record = new CheckinRecord();
        record.setGroupId(groupId);
        record.setUserId(userId);
        record.setCheckinDate(today);
        record.setCheckinTime(LocalDateTime.now());
        record.setGoldEarned(gold);
        record.setPointsEarned(points);
        checkinRecordMapper.insert(record);

        //更新统计信息
        UserStats stats = userStatsMapper.selectStats(groupId,userId);
        if (stats == null){
            stats = new UserStats();
            stats.setGroupId(groupId);
            stats.setUserId(userId);
            stats.setTotalCheckins(1);
            stats.setContinuousDays(1);
            stats.setLastCheckinDate(today);
            userStatsMapper.insert(stats);
        }else {
            stats.setTotalCheckins(stats.getTotalCheckins() + 1);
            if (today.minusDays(1).equals(stats.getLastCheckinDate())) {
                stats.setContinuousDays(stats.getContinuousDays() + 1);
            } else {
                stats.setContinuousDays(1);
            }
            stats.setLastCheckinDate(today);
            userStatsMapper.update(stats);
        }

        //获取排名数据
        int checkinRank = checkinRecordMapper.getTodayCheckinRank(groupId, userId, today);
        int pointsRank = groupMemberMapper.getPointsRank(groupId, userId);

        // 7. 返回结果
        CheckinResult result = new CheckinResult();
        result.setGoldEarned(gold);
        result.setPointsEarned(points);
        result.setTotalCheckins(stats.getTotalCheckins());
        result.setContinuousDays(stats.getContinuousDays());
        result.setCheckinRank(checkinRank);
        result.setPointsRank(pointsRank);
        return result;
    }
}
