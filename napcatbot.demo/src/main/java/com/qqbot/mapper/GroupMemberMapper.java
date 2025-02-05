package com.qqbot.mapper;

import com.qqbot.pojo.GroupMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GroupMemberMapper {

    //批量插入群组
    @Insert({
            "<script>",
            "INSERT INTO group_members (group_id, user_id, nickname,level,role)",
            "VALUES ",
            "<foreach collection='groupMembers' item='groupMember' separator=','>",
            "(#{groupMember.groupId},#{groupMember.userId},#{groupMember.nickname},#{groupMember.level},#{groupMember.role})",
            "</foreach>",
            "</script>"
    })
    int insert(@Param("groupMembers") List<GroupMember> groupMembers);


    // 增加金币和积分
    @Update("UPDATE group_members SET gold = gold + #{gold}, points = points + #{points} " +
            "WHERE group_id = #{groupId} AND user_id = #{userId}")
    int addGoldAndPoints(@Param("groupId") Long groupId,
                         @Param("userId") Long userId,
                         @Param("gold") int gold,
                         @Param("points") int points);

    // 查询积分排名
    @Select("SELECT COUNT(*) + 1 FROM group_members " +
            "WHERE group_id = #{groupId} AND points > " +
            "(SELECT points FROM group_members WHERE group_id = #{groupId} AND user_id = #{userId})")
    int getPointsRank(@Param("groupId") Long groupId, @Param("userId") Long userId);

}
