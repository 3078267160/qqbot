package com.qqbot.service;

import com.qqbot.mapper.GroupMemberMapper;
import com.qqbot.pojo.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GroupMemberService {

    @Autowired
    private GroupMemberMapper groupMemberMapper;

    @Transactional
    public Boolean saveBatchGroupMembers(List<GroupMember> groupMembers){
        int affectedRows = groupMemberMapper.insert(groupMembers);
        return affectedRows > 0;
    }
}
