package com.qqbot.Basics;


import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.qqbot.mapper.CheckinRecordMapper;
import com.qqbot.service.CheckinService;
import com.qqbot.tool.CheckinResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Shiro
@Component
public class SignSystem extends BotPlugin {

    @Autowired
    private CheckinService checkinService;

    @Autowired
    private CheckinRecordMapper checkinRecordMapper;

    @GroupMessageHandler
    @MessageHandlerFilter(cmd = "签到")
    public CheckinResult qd(Bot bot, GroupMessageEvent event){
        if ("签到".equals(event.getMessage())){
            //检查是否已签到
            LocalDate today = LocalDate.now();
            if (checkinRecordMapper.existsCheckinToday(event.getGroupId(), event.getUserId(),today)){
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\n今天已经签到过了，明天再来吧！")
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }else{
                CheckinResult result = checkinService.checkin(event.getGroupId(), event.getUserId());
                if (result != null){
                    //提取字段值
                    //构建消息
                    String sendMsg = MsgUtils.builder()
                            .at(event.getUserId())
                            .img("https://q2.qlogo.cn/headimg_dl?dst_uin="+event.getUserId()+"&spec=40")
                            .text(
                                    "\n\uD83C\uDF89签到成功"
                                    +"\n\uD83C\uDF81获得积分："+result.getPointsEarned()
                                    +"\n\uD83D\uDCB0获得金币："+result.getGoldEarned()
                                    +"\n⏰今日第"+result.getCheckinRank()+"位签到！"
                                    +"\n\uD83D\uDD25连续签到："+result.getContinuousDays()+"天"
                                    +"\n\uD83D\uDCC6累积签到："+result.getTotalCheckins()+"天"
                                    +"\n\uD83C\uDFC6积分排名：第"+result.getPointsRank()+"名"
                                    +"\nTips:更多功能开发联系管理员吧！"
                                )
                            .build();
                    bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
                }
            }
        }
        return null;
    }
}
