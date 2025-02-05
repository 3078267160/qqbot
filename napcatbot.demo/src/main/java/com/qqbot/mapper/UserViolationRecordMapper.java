package com.qqbot.mapper;

import com.qqbot.pojo.UserViolationRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 用户违规记录Mapper接口
 */
@Mapper
public interface UserViolationRecordMapper {

    /**
     * 插入用户违规记录
     *
     * @param record 用户违规记录对象
     */
    @Insert("INSERT INTO user_violation_records (group_id,user_id, violation_count, violation_level, last_violation_time) " +
            "VALUES (#{groupId},#{userId}, #{violationCount}, #{violationLevel}, #{lastViolationTime})")
    void insert(UserViolationRecord record);

    /**
     * 删除用户违规记录
     *
     * @param groupId userId 记录ID
     */
    @Delete("DELETE FROM user_violation_records WHERE group_id = #{groupId} AND user_id = #{userId}")
    void deleteById(Long groupId , Long userId);

    /**
     * 更新用户违规记录
     *
     * @param record 用户违规记录对象
     */
    @Update("UPDATE user_violation_records " +
            "SET user_id = #{userId} AND group_id = #{groupId} , violation_count = #{violationCount}, violation_level = #{violationLevel}, " +
            "last_violation_time = #{lastViolationTime} " +
            "WHERE group_id = #{groupId} AND user_id = #{userId}")
    void update(UserViolationRecord record);

    /**
     * 根据群+用户ID查询用户违规记录
     *
     * @param groupId,userId 用户ID
     * @return 用户违规记录对象
     */
    @Select("SELECT * FROM user_violation_records WHERE group_id = #{groupId} AND user_id = #{userId}")
    UserViolationRecord findByUserId(Long groupId , Long userId);

    /**
     * 查询所有用户违规记录
     *
     * @return 用户违规记录列表
     */
    @Select("SELECT * FROM user_violation_records")
    List<UserViolationRecord> findAll();

    /**
     * 更新用户的违规次数和级别
     *
     * @param userId         用户ID
     * @param newViolationCount 新的违规次数
     * @param newViolationLevel 新的违规级别
     * @param currentTime 当前时间
     */
    @Update("UPDATE user_violation_records " +
            "SET violation_count = #{newViolationCount}, violation_level = #{newViolationLevel}, " +
            "last_violation_time = #{currentTime} " +
            "WHERE group_id = #{groupId} AND user_id = #{userId}")
    void updateUserViolationInfo(@Param("groupId") Long groupId,
                                 @Param("userId") Long userId,
                                 @Param("newViolationCount") int newViolationCount,
                                 @Param("newViolationLevel") int newViolationLevel,
                                 @Param("currentTime") Date currentTime);
}
