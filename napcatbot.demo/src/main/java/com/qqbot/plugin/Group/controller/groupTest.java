package com.qqbot.plugin.Group.controller;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Shiro
@Component
public class groupTest {

    //设置管理员
    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "设置管理员")
    public void test1(Bot bot, GroupMessageEvent event) {
        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){

            String rawMessage = event.getMessage(); // 原始消息字符串
            List<String> atList = new ArrayList<>();

            // 正则匹配 [CQ:at,qq=数字]
            Pattern pattern = Pattern.compile("\\[CQ:at,qq=(\\d+)\\]");
            Matcher matcher = pattern.matcher(rawMessage);
            while (matcher.find()) {
                String qq = matcher.group(1); // 提取括号内的数字
                atList.add(qq);
                System.out.println("正则提取到QQ号：" + qq);
            }
            if (atList.size() == 1) {
                Long atq = Long.parseLong(atList.get(0));
                System.out.println(atq);
                bot.setGroupAdmin(event.getGroupId(),atq,true);
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\nQQ:"+atq+"已被设置为管理员！")
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }

        }else {
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("您没有权限，请联系管理员！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
    }

    //取消管理员
    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "取消管理员")
    public void test2(Bot bot, GroupMessageEvent event) {

        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){

            String rawMessage = event.getMessage(); // 原始消息字符串
            List<String> atList = new ArrayList<>();

            // 正则匹配 [CQ:at,qq=数字]
            Pattern pattern = Pattern.compile("\\[CQ:at,qq=(\\d+)\\]");
            Matcher matcher = pattern.matcher(rawMessage);
            while (matcher.find()) {
                String qq = matcher.group(1); // 提取括号内的数字
                atList.add(qq);
                System.out.println("正则提取到QQ号：" + qq);
            }
            if (atList.size() == 1) {
                Long atq = Long.parseLong(atList.get(0));
                System.out.println(atq);
                bot.setGroupAdmin(event.getGroupId(),atq,false);
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\nQQ:"+atq+"管理员已被取消！")
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }

        }else {
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("您没有权限，请联系管理员！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
    }

    //个人禁言
    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "禁言")
    public void test3(Bot bot, GroupMessageEvent event) {

        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){

            String rawMessage = event.getMessage(); // 原始消息字符串
            System.out.println(rawMessage);
            List<String> atList = new ArrayList<>();

            // 正则匹配 [CQ:at,qq=数字]
            Pattern pattern = Pattern.compile("\\[CQ:at,qq=(\\d+)\\]");
            Matcher matcher = pattern.matcher(rawMessage);
            while (matcher.find()) {
                String qq = matcher.group(1); // 提取括号内的数字
                atList.add(qq);
                System.out.println("正则提取到QQ号：" + qq);
            }
            // 正则表达式：匹配字符串末尾的数字
            Pattern pattern1 = Pattern.compile("(\\d+)$");
            Matcher matcher1 = pattern1.matcher(rawMessage);
            String number = "";
            if (matcher1.find()) {
                number = matcher1.group(1); // 提取匹配的数字
                System.out.println("禁言时间：" + number+"分钟");
            } else {
                System.out.println("未找到数字");
            }
            if (atList.size() == 1) {
                Long atq = Long.parseLong(atList.get(0));
                int jy = Integer.parseInt(number);
                int fz= 60;
                bot.setGroupBan(event.getGroupId(),atq,jy*fz);
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(atq)
                        .text("\n您已被关进小黑屋！")
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }

        }else {
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("您没有权限，请联系管理员！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
    }

    //个人解禁
    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "解禁")
    public void test4(Bot bot, GroupMessageEvent event) {

        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){
            String rawMessage = event.getMessage(); // 原始消息字符串
            List<String> atList = new ArrayList<>();

            // 正则匹配 [CQ:at,qq=数字]
            Pattern pattern = Pattern.compile("\\[CQ:at,qq=(\\d+)\\]");
            Matcher matcher = pattern.matcher(rawMessage);
            while (matcher.find()) {
                String qq = matcher.group(1); // 提取括号内的数字
                atList.add(qq);
                System.out.println("正则提取到QQ号：" + qq);
            }
            if (atList.size() == 1) {
                Long atq = Long.parseLong(atList.get(0));
                bot.setGroupBan(event.getGroupId(),atq,0);
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(atq)
                        .text("\n恭喜您刑满释放了！")
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }
        }else {
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("您没有权限，请联系管理员！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "我要头衔")
    public void test5(Bot bot, GroupMessageEvent event) {
        String regex = "我要头衔([^\\s]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(event.getMessage());
        // 查找匹配项
        if (matcher.find()) {
            // 获取匹配的子字符串（即“皇帝”）
            // group(1) 表示第一个捕获组，即括号内的内容
            String title = matcher.group(1);
            System.out.println("提取的头衔是：" + title);
            bot.setGroupSpecialTitle(event.getGroupId(),event.getUserId(),title,-1);
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n您已获得专属头衔："+title)
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);

        } else {
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n头衔获取格式：我要头衔+头衔名字！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
        }
    }

    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "删除头衔")
    public void test6(Bot bot, GroupMessageEvent event) {
            bot.setGroupSpecialTitle(event.getGroupId(),event.getUserId(),"",-1);
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("\n您已删除专属头衔！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
    }
}
