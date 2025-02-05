package com.qqbot.plugin.Group.controller;

import com.mikuac.shiro.annotation.GroupDecreaseHandler;
import com.mikuac.shiro.annotation.GroupIncreaseHandler;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.notice.GroupDecreaseNoticeEvent;
import com.mikuac.shiro.dto.event.notice.GroupIncreaseNoticeEvent;
import org.springframework.stereotype.Component;

@Shiro
@Component
public class welcome {
    /**
     * 加群事件
     * @param bot
     * @param event
     */
    @GroupIncreaseHandler
    public void onGroupMemberIncreasee(Bot bot, GroupIncreaseNoticeEvent event){
        long uid = event.getUserId();
        long gid = event.getGroupId();

        String welcome = MsgUtils.builder()
                .at(uid)
                .text("欢迎宝宝加入本群！")
                .build();

        bot.sendGroupMsg(gid,welcome,false);
    }

    /**
     * 退群事件
     * @param bot
     * @param event
     */
    @GroupDecreaseHandler
    public void onGroupMemberDecrease(Bot bot, GroupDecreaseNoticeEvent event){
        String leave = MsgUtils.builder()
                .text("报告！用户"+event.getUserId()+"已退出本群，再见！")
                .build();
        bot.sendGroupMsg(event.getGroupId(),leave,false);
    }
}
