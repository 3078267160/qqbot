package com.qqbot.service;

import com.qqbot.pojo.SensitiveWord;

import java.util.List;

/**
 * 敏感词Service接口
 */
public interface SensitiveWordService {

    /**
     * 插入敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    void addSensitiveWord(SensitiveWord sensitiveWord);

    /**
     * 删除敏感词
     *
     * @param id 敏感词ID
     */
    void removeSensitiveWord(Long id);

    /**
     * 更新敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    void updateSensitiveWord(SensitiveWord sensitiveWord);

    /**
     * 根据ID查询敏感词
     *
     * @param id 敏感词ID
     * @return 敏感词对象
     */
    SensitiveWord getSensitiveWordById(Long id);

    /**
     * 查询所有敏感词
     *
     * @return 敏感词列表
     */
    List<SensitiveWord> getAllSensitiveWords();

    /**
     * 根据敏感词查询敏感词
     * @param word
     * @return
     */
    SensitiveWord findByWord(String word);
}
