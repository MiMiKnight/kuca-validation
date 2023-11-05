package com.github.mimiknight.kuca.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
@DisplayName("测试 @KucaAssertFalse 校验注解")
public class KucaAssertFalseValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void test01() {
        log.info("test...");
    }
}