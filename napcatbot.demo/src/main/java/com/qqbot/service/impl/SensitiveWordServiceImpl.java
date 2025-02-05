package com.qqbot.service.impl;

import com.qqbot.mapper.SensitiveWordMapper;
import com.qqbot.pojo.SensitiveWord;
import com.qqbot.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 敏感词Service实现类
 */
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    /**
     * 插入敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    @Override
    public void addSensitiveWord(SensitiveWord sensitiveWord) {
        sensitiveWordMapper.insert(sensitiveWord);
    }

    /**
     * 删除敏感词
     *
     * @param id 敏感词ID
     */
    @Override
    public void removeSensitiveWord(Long id) {
        sensitiveWordMapper.deleteById(id);
    }

    /**
     * 更新敏感词
     *
     * @param sensitiveWord 敏感词对象
     */
    @Override
    public void updateSensitiveWord(SensitiveWord sensitiveWord) {
        sensitiveWordMapper.update(sensitiveWord);
    }

    /**
     * 根据ID查询敏感词
     *
     * @param id 敏感词ID
     * @return 敏感词对象
     */
    @Override
    public SensitiveWord getSensitiveWordById(Long id) {
        return sensitiveWordMapper.findById(id);
    }

    /**
     * 查询所有敏感词
     *
     * @return 敏感词列表
     */
    @Override
    public List<SensitiveWord> getAllSensitiveWords() {
        return sensitiveWordMapper.findAll();
    }

    /**
     * 根据敏感词查询敏感词
     * @param word
     * @return
     */
    @Override
    public SensitiveWord findByWord(String word) {
        return sensitiveWordMapper.findByWord(word);
    }

}