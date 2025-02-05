package com.qqbot.Basics;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.action.response.GroupInfoResp;
import com.mikuac.shiro.dto.action.response.GroupMemberInfoResp;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import com.qqbot.pojo.GroupMember;
import com.qqbot.service.GroupMemberService;
import com.qqbot.service.GroupService;
import com.qqbot.tool.GroupConverter;
import com.qqbot.tool.GroupMemberConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


//群信息存储
@Shiro
@Component
public class Group{

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMemberService groupMemberService;

    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED,cmd = "初始化")
    //初始化群信息
    public void GroupMsg(Bot bot,GroupMessageEvent event) {

        //获取群列表信息
        List<GroupInfoResp> groupInfoResps = bot.getGroupList().getData();
        List<com.qqbot.pojo.Group> groups = GroupConverter.convertToEntities(groupInfoResps);
        boolean isSuccess =  groupService.saveBatchGroups(groups);
        if (isSuccess){
            System.out.println("插入"+groups.size()+"条群数据成功！");
            System.out.println(groups.toString());
        }
        //获取群成员信息
        List<GroupMemberInfoResp> groupMemberInfoResps = bot.getGroupMemberList(event.getGroupId()).getData();
        List<GroupMember> groupMembers = GroupMemberConverter.convertToEntities(groupMemberInfoResps);
        boolean isSuccess1 = groupMemberService.saveBatchGroupMembers(groupMembers);
        if (isSuccess1){
            System.out.println("插入"+groupMembers.size()+"条成员数据成功！");
            System.out.println(groupMembers.toString());
        }

        //构建消息
        String sendMsg = MsgUtils.builder()
                .at(event.getUserId())
                .text("\n本群"+groupMembers.size()+"位成员数据初始化成功！")
                .build();
        bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
    }
}
