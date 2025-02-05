package com.qqbot.plugin.Group.controller;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotPlugin;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;

@Component
public class GroupMenu extends BotPlugin{

    @Override
    //群聊消息
    public int onGroupMessage(Bot bot, GroupMessageEvent event) {
        Long gid = 1019955788L;
        if (gid.equals(event.getGroupId())) {
            if ("随机白丝".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text(test())
                        .img(test1())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("每日一言".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\n"+test())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("美女视频".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test2(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("小姐姐系列".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test3(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("扭胯系列".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test4(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("爱情语录".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\n"+test5())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("舔狗语录".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .text("\n"+test6())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            }else if ("动漫系列".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test7(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            }else if ("治愈系列".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test8(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("讲个故事".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .voice(test9())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("随机唱鸭".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .voice(test10())
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            } else if ("高质量小姐姐".equals(event.getMessage())) {
                // 构建消息
                String sendMsg = MsgUtils.builder()
                        .at(event.getUserId())
                        .video(test11(),"https://api.lolimi.cn/API/dmtx/api.php")
                        .build();
                // 发送群消息
                bot.sendGroupMsg(event.getGroupId(), sendMsg, false);
            }
        }
        // 返回 MESSAGE_IGNORE 执行 plugin-list 下一个插件，返回 MESSAGE_BLOCK 则不执行下一个插件
        return MESSAGE_IGNORE;
    }

    public String test11(){
        try {
            URL url = new URL("https://api.cenguigui.cn/api/mp4/MP4_xiaojiejie.php?type=json");
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
                    String msg = data.get("url").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public String test10(){
        try {
            URL url = new URL("https://api.cenguigui.cn/api/singduck/");
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
                    String msg = data.get("audioSrc").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public String test9(){
        try {
            URL url = new URL("https://api.cenguigui.cn/api/music/ximalaya_gushi.php");
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
                    String msg = root.get("url").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public String test8(){
        try {
            URL url = new URL("https://api.yuafeng.cn/API/ly/zyxl.php?type=json");
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
                    String msg = root.get("Video").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public String test(){
        String yy = "https://api.52vmy.cn/api/wl/yan/yiyan";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(yy))
                    .build();

            CompletableFuture<HttpResponse<String>> responseCompletableFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> response = responseCompletableFuture.get();


            JSONObject json = JSONObject.parseObject(response.body());
            JSONObject data = json.getJSONObject("data");
            String msg = data.getString("hitokoto");
            System.out.println(msg);
            return msg;
        }catch (Exception e){

        }
        return null;
    };
    public String test5(){
        String yy = "https://api.yuafeng.cn/API/ly/aiqing.php?type=json";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(yy))
                    .build();

            CompletableFuture<HttpResponse<String>> responseCompletableFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> response = responseCompletableFuture.get();


            JSONObject json = JSONObject.parseObject(response.body());
            System.out.println(json);
            //取出JSON中需要用的值
            String msg = json.get("Msg").toString();
            return msg;
        }catch (Exception e){

        }
        return null;
    };
    public String test6(){
        String yy = "https://api.yuafeng.cn/API/ly/tiangou.php?type=json";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(yy))
                    .build();

            CompletableFuture<HttpResponse<String>> responseCompletableFuture =
                    client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            HttpResponse<String> response = responseCompletableFuture.get();


            JSONObject json = JSONObject.parseObject(response.body());
            System.out.println(json);
            //取出JSON中需要用的值
            String msg = json.get("Msg").toString();
            return msg;
        }catch (Exception e){

        }
        return null;
    };

    public String test1(){
        String bs = "https://v2.xxapi.cn/api/baisi";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(bs))
                    .build();

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);
            String msg = jsonNode.get("data").asText();
            System.out.println(msg);
            return msg;

        }catch (Exception e){

        }
        return null;
    };

    public String test2(){
        try {
            URL url = new URL("https://api.52vmy.cn/api/video/girl");
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
                //System.out.println(response.toString());

                //取出JSON中需要用的值
                ObjectMapper mapper = new ObjectMapper();
                try {
                    //解析JSON字符串为jsonnode对象
                    JsonNode root = mapper.readTree(response.toString());

                    //获取data节点
                    JsonNode data = root.get("data");
                    String msg = data.get("video").asText();
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };
    public String test3(){
        try {
            URL url = new URL("https://tucdn.wpon.cn/api-girl/index.php?wpon=json");
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
                    String msg = root.get("mp4").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };
    public String test4(){
        try {
            URL url = new URL("https://api.yuafeng.cn/API/ly/sjxl.php?type=json");
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
                    String msg = root.get("Video").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };
    public String test7(){
        try {
            URL url = new URL("https://api.yuafeng.cn/API/ly/dmxl.php?type=json");
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
                    String msg = root.get("Video").asText();
                    System.out.println(msg);
                    return msg;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("请求失败，请联系管理员处理！");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };
}
