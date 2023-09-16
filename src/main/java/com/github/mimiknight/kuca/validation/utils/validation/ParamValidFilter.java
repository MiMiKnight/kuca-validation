package com.github.mimiknight.kuca.validation.utils.validation;

public class ParamValidFilter {

    private ParamValidFilter() {
    }

    public static ParamValidFilter start() {
        return new ParamValidFilter();
    }

    public ParamValidFilter validNull(Object value, RuntimeException ex) {
        new ValidateNull().run(value, ex);
        return this;
    }

    public ParamValidFilter validNotNull(Object value, RuntimeException ex) {
        new ValidateNotNull().run(value, ex);
        return this;
    }

    public ParamValidFilter validEmpty(Object value, RuntimeException ex) {
        new ValidateEmpty().run(value, ex);
        return this;
    }

    public void end() {
    }
}
