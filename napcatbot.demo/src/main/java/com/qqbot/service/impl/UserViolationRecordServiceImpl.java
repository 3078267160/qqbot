package com.qqbot.service.impl;

import com.qqbot.mapper.UserViolationRecordMapper;
import com.qqbot.pojo.SensitiveWord;
import com.qqbot.pojo.UserViolationRecord;
import com.qqbot.service.UserViolationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户违规记录Service实现类
 */
@Service
public class UserViolationRecordServiceImpl implements UserViolationRecordService {

    @Autowired
    private UserViolationRecordMapper userViolationRecordMapper;

    /**
     * 插入用户违规记录
     *
     * @param record 用户违规记录对象
     */
    @Override
    public void addUserViolationRecord(UserViolationRecord record) {
        userViolationRecordMapper.insert(record);
    }

    /**
     * 删除用户违规记录
     *
     * @param groupId,userId 记录ID
     */
    @Override
    public void removeUserViolationRecord(Long groupId ,Long userId) {
        userViolationRecordMapper.deleteById( groupId ,userId);
    }

    /**
     * 更新用户违规记录
     *
     * @param record 用户违规记录对象
     */
    @Override
    public void updateUserViolationRecord(UserViolationRecord record) {
        userViolationRecordMapper.update(record);
    }

    /**
     * 根据用户ID查询用户违规记录
     *
     * @param userId 用户ID
     * @return 用户违规记录对象
     */
    @Override
    public UserViolationRecord getUserViolationRecordByUserId(Long groupId , Long userId) {
        return userViolationRecordMapper.findByUserId(groupId , userId);
    }

    /**
     * 查询所有用户违规记录
     *
     * @return 用户违规记录列表
     */
    @Override
    public List<UserViolationRecord> getAllUserViolationRecords() {
        return userViolationRecordMapper.findAll();
    }

    /**
     * 处理用户违规行为
     *
     * @param userId 用户ID
     */
    @Override
    public void handleUserViolation(Long groupId , Long userId) {
        UserViolationRecord record = userViolationRecordMapper.findByUserId(groupId,userId);
        if (record == null) {
            // 如果用户没有违规记录，则插入一条新的记录
            record = new UserViolationRecord();
            record.setGroupId(groupId);
            record.setUserId(userId);
            record.setViolationCount(1);
            record.setViolationLevel(0);
            record.setLastViolationTime(new Date());
            userViolationRecordMapper.insert(record);
        } else {
            // 如果用户已有违规记录，则更新记录
            int currentViolationCount = record.getViolationCount();
            int newViolationCount = currentViolationCount + 1;
            int newViolationLevel = record.getViolationLevel();

            if (newViolationCount <= 5) {
                // 前5次撤回+警告
                System.out.println("User ID: " + userId + ", Violation Count: " + newViolationCount + ", Action: Withdrawal + Warning");
            } else if (newViolationCount > 5 && newViolationCount <= 10) {
                // 超过5次后违规等级升到1级禁言，6-10次为禁言+警告
                newViolationLevel = 1;
                System.out.println("User ID: " + userId + ", Violation Count: " + newViolationCount + ", Action: Mute + Warning");
            } else {
                // 超过10次后违规等级升到2级踢出，再次违规后踢出群聊
                newViolationLevel = 2;
                System.out.println("User ID: " + userId + ", Violation Count: " + newViolationCount + ", Action: Kick Out");
            }

            record.setViolationCount(newViolationCount);
            record.setViolationLevel(newViolationLevel);
            record.setLastViolationTime(new Date());

            userViolationRecordMapper.updateUserViolationInfo(
                    groupId, userId, newViolationCount, newViolationLevel, new Date()
            );
        }
    }
}
