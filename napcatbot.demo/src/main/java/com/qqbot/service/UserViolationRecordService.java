package com.qqbot.service;

import com.qqbot.pojo.SensitiveWord;
import com.qqbot.pojo.UserViolationRecord;

import java.util.List;

/**
 * 用户违规记录Service接口
 */
public interface UserViolationRecordService {

    /**
     * 插入用户违规记录
     *
     * @param record 用户违规记录对象
     */
    void addUserViolationRecord(UserViolationRecord record);

    /**
     * 删除用户违规记录
     *
     * @param id 记录ID
     */
    void removeUserViolationRecord(Long groupId ,Long userId);

    /**
     * 更新用户违规记录
     *
     * @param record 用户违规记录对象
     */
    void updateUserViolationRecord(UserViolationRecord record);

    /**
     * 根据用户ID查询用户违规记录
     *
     * @param userId 用户ID
     * @return 用户违规记录对象
     */
    UserViolationRecord getUserViolationRecordByUserId(Long groupId , Long userId);

    /**
     * 查询所有用户违规记录
     *
     * @return 用户违规记录列表
     */
    List<UserViolationRecord> getAllUserViolationRecords();

    /**
     * 处理用户违规行为
     *
     * @param userId 用户ID
     */
    void handleUserViolation(Long groupId , Long userId);
}
