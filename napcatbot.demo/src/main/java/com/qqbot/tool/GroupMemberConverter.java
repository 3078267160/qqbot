package com.qqbot.tool;

import com.mikuac.shiro.dto.action.response.GroupMemberInfoResp;
import com.qqbot.pojo.GroupMember;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMemberConverter {

    public static GroupMember convertToEntity(GroupMemberInfoResp resp){
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(resp.getGroupId());
        groupMember.setUserId(resp.getUserId());
        groupMember.setNickname(resp.getNickname());
        groupMember.setLevel(resp.getLevel());
        groupMember.setRole(resp.getRole());
        return groupMember;
    }

    public static List<GroupMember> convertToEntities(List<GroupMemberInfoResp> respList){
        return respList.stream()
                .map(GroupMemberConverter::convertToEntity)
                .collect(Collectors.toList());
    }
}
