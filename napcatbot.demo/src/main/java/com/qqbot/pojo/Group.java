package com.qqbot.pojo;

import lombok.Data;

@Data
public class Group {
    private Long groupId;
    private String groupName;
    private String groupMemo;
    private Long groupCreateTime;
    private Integer groupLevel;
    private Integer memberCount;
    private Integer maxMemberCount;
}
