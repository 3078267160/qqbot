package com.qqbot.plugin;

import org.springframework.stereotype.Component;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;

@Component
public class TsetPlugin extends BotPlugin{

    @Override
    public int onPrivateMessage(Bot bot, PrivateMessageEvent event) {
        if ("hello".equals(event.getMessage())) {
            // 构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("hello, this is napcatdemo plugin.")
                    .build();
            bot.sendPrivateMsg(event.getUserId(), sendMsg, false);
        }
        // 返回 MESSAGE_IGNORE 执行 plugin-list 下一个插件，返回 MESSAGE_BLOCK 则不执行下一个插件
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(Bot bot, GroupMessageEvent event) {
        if ("秘籍".equals(event.getMessage())){
            // 构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("       \n     \uD83C\uDF89小沫功能\uD83C\uDF89"
                            +"\n==============="
                            +"\n  每日一言  随机黑丝"
                            +"\n  随机白丝  美女视频"
                            +"\n  小姐姐系列(低质量?)"
                            +"\n  动漫系列  治愈系列"
                            +"\n  扭胯系列(低质量?)"
                            +"\n  爱情语录  舔狗语录"
                            +"\n  讲个故事  随机唱鸭"
                            +"\n  高质量小姐姐"
                            +"\n  AI大模型：#+问题"
                            +"\n  设置/取消管理员"
                            +"\n  禁言/解禁 点歌+歌名"
                            +"\n  有问题请联系管理员！"
                            +"\n  联系QQ:3078267160"
                    )
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
        // 返回 MESSAGE_IGNORE 执行 plugin-list 下一个插件，返回 MESSAGE_BLOCK 则不执行下一个插件
        return MESSAGE_IGNORE;
    }

}
