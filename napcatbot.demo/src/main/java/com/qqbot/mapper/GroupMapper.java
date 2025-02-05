package com.qqbot.mapper;

import com.qqbot.pojo.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {

    //查询所有群组
    @Select("select * from group_info")
    List<Group> findAll();

    //批量插入群组
    @Insert({
            "<script>",
            "INSERT INTO group_info (group_id, group_name, member_count, max_member_count)",
            "VALUES ",
            "<foreach collection='groups' item='group' separator=','>",
            "(#{group.groupId}, #{group.groupName}, #{group.memberCount}, #{group.maxMemberCount})",
            "</foreach>",
            "</script>"
    })
    int insert(@Param("groups")List<Group> groups);
}
