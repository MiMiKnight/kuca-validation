package com.github.mimiknight.kuca.validation.utils.validation;

import com.github.mimiknight.kuca.validation.utils.ParamValidUtils;

public class ValidateNotEmpty implements Validate {

    @Override
    public void run(Object value, RuntimeException ex) {
        ParamValidUtils.validNull(value, ex);
    }
}
