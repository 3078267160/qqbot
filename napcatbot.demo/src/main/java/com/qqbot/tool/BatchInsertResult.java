package com.qqbot.tool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchInsertResult<T> {
    private boolean success;  // 操作是否成功
    private String message;   // 提示信息
    private long costMs;      // 耗时（毫秒）
    private T data;           // 返回数据（可选）


    // 快速构建成功响应
    public static <T> BatchInsertResult<T> success(long costMs, T data) {
        return new BatchInsertResult<>(true, "操作成功", costMs, data);
    }

    // 快速构建失败响应
    public static <T> BatchInsertResult<T> fail(long costMs, String message) {
        return new BatchInsertResult<>(false, message, costMs, null);
    }
}
