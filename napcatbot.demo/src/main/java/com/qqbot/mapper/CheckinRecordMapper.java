package com.qqbot.mapper;


import com.qqbot.pojo.CheckinRecord;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;

@Mapper
public interface CheckinRecordMapper {

    // 插入记录
    @Insert("INSERT INTO checkin_records (group_id, user_id, checkin_date, checkin_time, gold_earned, points_earned) " +
            "VALUES (#{groupId}, #{userId}, #{checkinDate}, #{checkinTime}, #{goldEarned}, #{pointsEarned})")
    @Options(useGeneratedKeys = true, keyProperty = "recordId")
    void insert(CheckinRecord record);

    // 检查今日是否签到
    @Select("SELECT COUNT(*) FROM checkin_records " +
            "WHERE group_id = #{groupId} AND user_id = #{userId} AND checkin_date = #{date}")
    boolean existsCheckinToday(@Param("groupId") Long groupId,
                               @Param("userId") Long userId,
                               @Param("date") LocalDate date);

    // 获取今日签到排名
    @Select("SELECT COUNT(*) + 1 FROM checkin_records " +
            "WHERE group_id = #{groupId} AND checkin_date = #{date} AND checkin_time < " +
            "(SELECT checkin_time FROM checkin_records WHERE group_id = #{groupId} AND user_id = #{userId} AND checkin_date = #{date})")
    int getTodayCheckinRank(@Param("groupId") Long groupId,
                            @Param("userId") Long userId,
                            @Param("date") LocalDate date);
}
