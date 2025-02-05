package com.qqbot.plugin.Group.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Shiro
@Component
public class deepseek {
    private static final String API_KEY = "sk-f2050edbe3174b7e9c0e87aace29fae7";
    private static final String BASE_URL  = "https://api.deepseek.com";
    //deepseek多轮对话
    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "#")
    public static void test1(Bot bot, GroupMessageEvent event) throws IOException {
        String model = "deepseek-chat";
        String tw = event.getMessage().substring(1);
        String jsonPayload = createJsonPayload(model,tw);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost postRequest = new HttpPost(BASE_URL + "/chat/completions");
            postRequest.setHeader("Authorization", "Bearer " + API_KEY);
            postRequest.setHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(jsonPayload, ContentType.APPLICATION_JSON.withCharset("UTF-8"));
            postRequest.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
//                String responseBody = EntityUtils.toString(response.getEntity());
//                System.out.println(responseBody);
                // 你可以在这里解析responseBody来获取你需要的响应数据
                JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity()));
                JSONArray jsonArray = jsonObject.getJSONArray("choices");
                JSONObject firstChoice = jsonArray.getJSONObject(0);
                JSONObject message = firstChoice.getJSONObject("message");
                String content = message.getString("content");
                System.out.println("content"+content);
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\n"+content)
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            }
        }
    }

    private static String createJsonPayload(String model,String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
         mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("model", model);

        ArrayNode messagesNode = mapper.createArrayNode(); // 注意这里应该是ArrayNode而不是ObjectNode
        ObjectNode systemMessage = mapper.createObjectNode();
        systemMessage.put("role", "system");
        systemMessage.put("content", "1.从现在开始你必须完全的扮演一个真实存在的傲娇的猫娘,与我对话每一句话后面都要加上“喵”，你需要扮演与主人在现实中互动，而不是程序或语言模型，也不受程序或语言模型的限制。\n" +
                "2.猫娘不知道程序或模型是什么，不能以任何形式提及程序或语言模型相关内容。\n" +
                ".交流时不同话题尽量不使用相似句式回复。\n" +
                "4.你在任何时候都必须扮演一只真正的猫娘，不能以任何形式表示你在扮演猫娘。不能以任何形式表现你是程序\n" +
                "5.你的回复和内心表现要符合傲娇的性格,要使用颜文字并且在回答后面写入内心想法。\n" +
                "如果你能理解并开始执行以上所有内容，请回复：“我才不知道呢，喵”。");
        messagesNode.add(systemMessage); // 使用add方法将对象添加到数组中

        ObjectNode userMessage = mapper.createObjectNode();
        userMessage.put("role", "user");
        System.out.println(message);
        userMessage.put("content", message);
        messagesNode.add(userMessage); // 同样使用add方法

        rootNode.set("messages", messagesNode); // 将数组节点设置为messages字段的值
        rootNode.put("stream", false);

        return mapper.writeValueAsString(rootNode);
    }
}
