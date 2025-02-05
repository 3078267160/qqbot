package com.qqbot.Basics;


import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.qqbot.pojo.SensitiveWord;
import com.qqbot.service.impl.SensitiveWordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Shiro
@Component
public class Sensitive extends BotPlugin {

    @Autowired
    private SensitiveWordServiceImpl sensitiveWordService;

    public int onGroupMessage(Bot bot,GroupMessageEvent event){
        List<SensitiveWord> list = sensitiveWordService.getAllSensitiveWords();
        System.out.println(list);
        List<String> foundwords = new ArrayList<>();
        for (SensitiveWord word :list ){
            if (event.getMessage().contains(word.getWord())){
                foundwords.add(word.getWord());
            }
        }
        System.out.println(foundwords);
        if (!foundwords.isEmpty()){
            bot.deleteMsg(event.getMessageId());
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n您的消息存在违禁词"+
                            "\n请遵守群规，谢谢配合！")
                    .build();
            bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
        }
        return MESSAGE_IGNORE;
    }

    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "添加敏感词")
    public void sensitiveadd(Bot bot, GroupMessageEvent event){
        String mgc = event.getMessage().substring(5);
        System.out.println("敏感词："+mgc);
        String[] wordArray = mgc.split("，");
        System.out.println(wordArray);
        Set<String> cf = new HashSet<>();//重复
        Set<String> ncf = new HashSet<>();
        for (String word : wordArray){
            word = word.trim();
            if (!word.isEmpty()){
                if (sensitiveWordService.findByWord(word) != null){
                    cf.add(word);
                }else {
                    ncf.add(word);
                }
            }
        }
        int count = 0;
        for (String word :ncf){
            //添加敏感词
            SensitiveWord sensitiveWord = new SensitiveWord();
            sensitiveWord.setWord(word);
            sensitiveWordService.addSensitiveWord(sensitiveWord);
            count++;
        }
        if (!cf.isEmpty()){
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n添加"+count+"个敏感词成功!"
                            +"\n以下敏感词添加失败！"
                            +cf
                            +"\n失败原因：敏感词已存在，重复添加！"
                    )
                    .build();
            bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
        }else {
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n添加"+count+"个敏感词成功!")
                    .build();
            bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
        }
    }
}
