package com.liangma.migration.logs;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger implements ILogger {

    private void console(@NotNull CharSequence charSequence) {
        System.out.println(charSequence);
    }

    private void console(@NotNull Throwable throwable) {
        System.out.println(throwable.getMessage());
        for (StackTraceElement ele : throwable.getStackTrace()) {
            System.out.println(ele.toString());
        }
    }

    private void console(@NotNull CharSequence charSequence, @NotNull Throwable throwable) {
        console(charSequence);
        console(throwable);
    }


    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public void debug(CharSequence charSequence) {
        if (isDebugEnabled()) {
            console(charSequence);
        }
    }

    @Override
    public void debug(CharSequence charSequence, Throwable throwable) {
        if (isDebugEnabled()) {
            console(charSequence, throwable);
        }
    }

    @Override
    public void debug(Throwable throwable) {
        if (isDebugEnabled()) {
            console(throwable);
        }
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public void info(CharSequence charSequence) {
        if (isInfoEnabled()) {
            console(charSequence);
        }
    }

    @Override
    public void info(CharSequence charSequence, Throwable throwable) {
        if (isInfoEnabled()) {
            console(charSequence, throwable);
        }
    }

    @Override
    public void info(Throwable throwable) {
        if (isInfoEnabled()) {
            console(throwable);
        }
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void warn(CharSequence charSequence) {
        if (isWarnEnabled()) {
            console(charSequence);
        }
    }

    @Override
    public void warn(CharSequence charSequence, Throwable throwable) {
        if (isWarnEnabled()) {
            console(charSequence, throwable);
        }
    }

    @Override
    public void warn(Throwable throwable) {
        if (isWarnEnabled()) {
            console(throwable);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void error(CharSequence charSequence) {
        if (isErrorEnabled()) {
            console(charSequence);
        }
    }

    @Override
    public void error(CharSequence charSequence, Throwable throwable) {
        if (isWarnEnabled()) {
            console(charSequence, throwable);
        }
    }

    @Override
    public void error(Throwable throwable) {
        if (isErrorEnabled()) {
            console(throwable);
        }
    }
}
