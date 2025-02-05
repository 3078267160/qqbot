package com.qqbot.plugin.Group.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Shiro
@Component
public class test1 {

    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED,cmd = "全体禁言")
    //艾特全体禁言
    public void test1(Bot bot,GroupMessageEvent event) {
        //开启全体禁言
        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){
            bot.setGroupWholeBan(event.getGroupId(), true);
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("全体禁言已开启！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
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
    @MessageHandlerFilter(at = AtEnum.NEED,cmd = "解除禁言")
    //艾特解除禁言
    public void test2(Bot bot,GroupMessageEvent event) {
        //解除全体禁言
        Long gl = 3078267160l;
        if (gl.equals(event.getUserId())){
            bot.setGroupWholeBan(event.getGroupId(), false);
            //构建消息
            String sendMsg = MsgUtils.builder()
                    .at(event.getUserId())
                    .text("全体禁言已解除！")
                    .build();
            // 发送群消息
            bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
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
    @MessageHandlerFilter(endWith = "天气")
    //天气测试
    public void tianqi(Bot bot,GroupMessageEvent event){
        String city = event.getMessage().substring(0,event.getMessage().indexOf("天气"));
        try {
            URL url = new URL("https://api.52vmy.cn/api/query/tian?city="+city);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求方法为GET
            connection.setRequestMethod("GET");

            // 允许输入流（默认为true）
            connection.setDoInput(true);

            // 不允许输出流（因为我们是GET请求）
            connection.setDoOutput(false);
            // 获取响应码
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            // 如果响应码是200（OK），则读取响应内容
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印响应内容
                System.out.println(response.toString());

                //取出JSON中需要用的值
                ObjectMapper mapper = new ObjectMapper();
                try {
                    //解析JSON字符串为jsonnode对象
                    JsonNode root = mapper.readTree(response.toString());

                    //获取data节点
                    JsonNode data = root.get("data");

                    //获取warning节点
                    JsonNode warning = data.get("warning");

                    //提取data节点中各个字段的值
                    String city1 = data.get("city").asText();//城市
                    String weather = data.get("weather").asText();//天气
                    String wind = data.get("wind").asText();//风向
                    String windSpeed = data.get("windSpeed").asText();//风力
                    String time = data.get("time").asText();//时间
                    //String warning1 = warning.get("warning").asText();//预警
                    //构建消息
                    String sendMsg = MsgUtils.builder()
                            .at(event.getUserId())
                            .text
                                    ("\n"+"地区："+city1+"\n"
                                            +"天气："+weather+"\n"
                                            +"风向："+wind+"\n"
                                            +"风力："+windSpeed+"\n"
                                            +"时间："+time+"\n"
                                    )
                            .build();
                    // 发送群消息
                    bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
