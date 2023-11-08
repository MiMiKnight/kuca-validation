package com.github.mimiknight.kuca.validation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 注解参数校验错误提示信息对象
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-11-08 22:00:46
 */
@Accessors(chain = true)
@Setter
@Getter
public class AnnotationParamValidErrorTip {

    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 错误提示消息
     */
    private String message;
}
