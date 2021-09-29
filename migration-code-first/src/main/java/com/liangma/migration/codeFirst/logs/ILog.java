package com.liangma.migration.codeFirst.logs;

public interface ILog {

    default boolean isDebugEnabled() {
        return false;
    }

    void debug(CharSequence charSequence);

    void debug(CharSequence charSequence, Throwable throwable);

    void debug(Throwable throwable);

    default boolean isInfoEnabled() {
        return false;
    }

    void info(CharSequence charSequence);

    void info(CharSequence charSequence, Throwable throwable);

    void info(Throwable throwable);

    default boolean isWarnEnabled() {
        return false;
    }

    void warn(CharSequence charSequence);

    void warn(CharSequence charSequence, Throwable throwable);

    void warn(Throwable throwable);

    default boolean isErrorEnabled() {
        return false;
    }

    void error(CharSequence charSequence);

    void error(CharSequence charSequence, Throwable throwable);

    void error(Throwable throwable);
}
