package com.qqbot.mapper;

import com.qqbot.pojo.UserStats;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserStatsMapper {

    // 查询统计信息
    @Select("SELECT * FROM user_stats WHERE group_id = #{groupId} AND user_id = #{userId}")
    UserStats selectStats(@Param("groupId") Long groupId, @Param("userId") Long userId);

    // 插入统计信息
    @Insert("INSERT INTO user_stats (group_id, user_id, total_checkins, continuous_days, last_checkin_date) " +
            "VALUES (#{groupId}, #{userId}, #{totalCheckins}, #{continuousDays}, #{lastCheckinDate})")
    void insert(UserStats stats);

    // 更新统计信息
    @Update("UPDATE user_stats SET total_checkins = #{totalCheckins}, continuous_days = #{continuousDays}, last_checkin_date = #{lastCheckinDate} " +
            "WHERE group_id = #{groupId} AND user_id = #{userId}")
    void update(UserStats stats);
}
