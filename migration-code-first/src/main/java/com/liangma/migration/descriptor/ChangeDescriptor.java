package com.liangma.migration.descriptor;

import java.util.List;

/**
 * 变更描述符
 */
public class ChangeDescriptor<T> {

    public final T origin;

    public final T current;

    private final List<String> changedList;

    public ChangeDescriptor(T origin, T current, List<String> changedList) {
        this.origin = origin;
        this.current = current;
        this.changedList = changedList;
    }

    public boolean hasChanged(String item) {
        return changedList.contains(item);
    }
}


