package com.qqbot.tool;

import com.mikuac.shiro.dto.action.response.GroupInfoResp;
import com.qqbot.pojo.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupConverter {

    public static Group convertToEntity(GroupInfoResp resp){
        Group group = new Group();
        group.setGroupId(resp.getGroupId());
        group.setGroupName(resp.getGroupName());
        group.setGroupMemo(resp.getGroupMemo());
        group.setGroupCreateTime(group.getGroupCreateTime());
        group.setGroupLevel(resp.getGroupLevel());
        group.setMemberCount(resp.getMemberCount());
        group.setMaxMemberCount(resp.getMaxMemberCount());
        return group;
    }

    public static List<Group> convertToEntities(List<GroupInfoResp> respList){
        return respList.stream()
                .map(GroupConverter::convertToEntity)
                .collect(Collectors.toList());
    }
}
