package com.qqbot.controller;


import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.qqbot.service.CheckinService;
import com.qqbot.tool.CheckinResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;


    @PostMapping("/{groupId}/{userId}")
    public CheckinResult checkin(@PathVariable Long groupId,@PathVariable Long userId){
        System.out.println("签到成功");
        return checkinService.checkin(groupId,userId);
    }
}
