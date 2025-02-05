package com.qqbot.controller;

import com.qqbot.pojo.Group;
import com.qqbot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // 查询所有群组
    @GetMapping()
    public List<Group> listGroups() {

        return groupService.findAll();
    }

    //批量新增
    @PostMapping("/save")
    public boolean addGroups(@RequestBody List<Group> groups){
        return groupService.saveBatchGroups(groups);
    }
}
