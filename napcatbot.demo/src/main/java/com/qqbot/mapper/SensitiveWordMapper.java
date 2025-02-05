package com.qqbot.mapper;

import com.qqbot.pojo.SensitiveWord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 敏感词Mapper接口
 */
@Mapper
public interface SensitiveWordMapper {

    /**
     * 插入敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    @Insert("INSERT INTO sensitive_words (word) VALUES (#{word})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 自增ID
    void insert(SensitiveWord sensitiveWord);

    /**
     * 删除敏感词
     *
     * @param id 敏感词ID
     */
    @Delete("DELETE FROM sensitive_words WHERE id = #{id}")
    void deleteById(Long id);

    /**
     * 更新敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    @Update("UPDATE sensitive_words SET word = #{word} WHERE id = #{id}")
    void update(SensitiveWord sensitiveWord);

    /**
     * 根据ID查询敏感词
     *
     * @param id 敏感词ID
     * @return 敏感词对象
     */
    @Select("SELECT * FROM sensitive_words WHERE id = #{id}")
    SensitiveWord findById(Long id);

    /**
     * 查询所有敏感词
     *
     * @return 敏感词列表
     */
    @Select("SELECT * FROM sensitive_words")
    List<SensitiveWord> findAll();

    /**
     * 根据敏感词内容查询敏感词
     *
     * @param word 敏感词内容
     * @return 敏感词对象
     */
    @Select("SELECT * FROM sensitive_words WHERE word = #{word}")
    SensitiveWord findByWord(String word);
}