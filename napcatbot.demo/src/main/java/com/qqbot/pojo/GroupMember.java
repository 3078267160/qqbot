package com.qqbot.pojo;

import lombok.Data;

@Data
public class GroupMember {
    private Long groupId;
    private Long userId;
    private String nickname;
    private String level;
    private String role;
    private Long gold;
    private Long points;
}
