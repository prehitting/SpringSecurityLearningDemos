package com.prehitting.common.model;

/**
 * 常用API返回对象接口
 *
 * @author macro
 * @date 2019/4/19
 */
public interface IErrorCode {
    /**
     * 返回码
     * @return code
     */
    long getCode();

    /**
     * 返回信息
     * @return message
     */
    String getMessage();
}
