package com.food.ordering.system.domain.value;

import java.util.Objects;

public abstract class Identity<T> {

    private final T value;

    public Identity(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity<?> identity = (Identity<?>) o;
        return Objects.equals(value, identity.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
