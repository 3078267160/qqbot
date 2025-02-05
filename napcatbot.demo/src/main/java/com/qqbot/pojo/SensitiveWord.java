package com.qqbot.pojo;

import lombok.Data;

/**
 * 敏感词实体类
 */
@Data
public class SensitiveWord {
    private Long id; // 主键ID
    private String word; // 敏感词
}