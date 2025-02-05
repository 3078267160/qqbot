package com.qqbot.service;

import com.qqbot.mapper.GroupMapper;
import com.qqbot.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//群组服务
@Service
public class GroupService{

    @Autowired
    private GroupMapper groupMapper;

    public List<Group> findAll(){
        return groupMapper.findAll();
    }

    @Transactional
    public Boolean saveBatchGroups(List<Group> groups){
        int affectedRows = groupMapper.insert(groups);
        return affectedRows > 0;
    }
}
