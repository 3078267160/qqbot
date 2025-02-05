package com.qqbot.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 用户违规记录实体类
 */
@Data
public class UserViolationRecord {
    private Long groupId;//群ID
    private Long userId; // 用户ID
    private Integer violationCount; // 违规次数，默认为0
    private Integer violationLevel; // 违规等级，默认为0
    private Date lastViolationTime; // 最后一次违规时间
    private Date createdAt; // 创建时间
    private Date updatedAt; // 更新时间
}
