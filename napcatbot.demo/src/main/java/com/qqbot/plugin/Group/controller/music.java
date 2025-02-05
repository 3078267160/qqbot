package com.qqbot.plugin.Group.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Shiro
@Component
//音乐卡片发送
public class music {

    @GroupMessageHandler
    @MessageHandlerFilter(startWith = "点歌")
    public MessageWrapper messageWrapper(Bot bot,GroupMessageEvent event) {
        String gm = event.getMessage().substring(2);
        System.out.println(gm);
        try {
            URL url = new URL("https://api.52vmy.cn/api/music/qq?msg="+gm+"&n=1");
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
                    //解析JSON字符串为jsonnode对象
                    JsonNode root = mapper.readTree(response.toString());

                    //获取data节点
                    JsonNode data = root.get("data");

                    //提取data节点中各个字段的值
                    String songid = data.get("songid").asText();//歌曲ID
                    String url1 = data.get("url").asText();//歌曲url
                    String singer = data.get("singer").asText();//歌手
                    String song = data.get("song").asText();//歌名
                    String picture = data.get("picture").asText();//歌曲图片
                //构建消息
                String sendMsg = MsgUtils.builder()
                        .customMusic(url1,url1,singer,song,picture)
                        .build();
                bot.sendGroupMsg(event.getGroupId(),sendMsg,false);
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    };


    // 包装类（可选，取决于您的使用场景）
    public class MessageWrapper {
        private List<MusicMessage> messages;

        // Getters and Setters
        public List<MusicMessage> getMessages() {
            return messages;
        }

        public void setMessages(List<MusicMessage> messages) {
            this.messages = messages;
        }
    }

    // 实体类
    public class MusicMessage {
        private String type;
        private CustomMusicData data;

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public CustomMusicData getData() {
            return data;
        }

        public void setData(CustomMusicData data) {
            this.data = data;
        }

        // 嵌套类，用于表示自定义音乐数据
        public static class CustomMusicData {
            private String type;
            private String url;
            private String audio;
            private String title;
            private String image;

            // Getters and Setters
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getAudio() {
                return audio;
            }

            public void setAudio(String audio) {
                this.audio = audio;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }

}
