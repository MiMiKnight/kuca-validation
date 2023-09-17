package com.github.mimiknight.kuca.validation.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParamValidateAspect {

    @Pointcut("")
    private void pointcut() {
    }

    @Before(value = "pointcut()&& args(request,..)", argNames = "request")
    public void before(String request) {
        // TODO document why this method is empty
    }
}
