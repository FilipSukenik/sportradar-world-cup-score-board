package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

public abstract class DependencyFactory {

    private final Map<Class<?>, Object> dependenciesMap;

    private boolean initialized;

    protected DependencyFactory() {

        dependenciesMap = new HashMap<>();
    }

    protected DependencyFactory(@NotNull Map<Class<?>, Object> dependenciesMap) {

        Assert.notNull(dependenciesMap, "dependenciesMap cannot be null");

        this.dependenciesMap = dependenciesMap;
        initialized = true;
    }

    @NotNull
    public final <T> T getDependency(@NotNull Class<T> clazz) {

        if (!initialized) {
            throw new IllegalStateException("Dependency factory has not been initialized.");
        }

        Assert.notNull(clazz, "clazz cannot be null");

        Object dependency = dependenciesMap.get(clazz);
        if (null == dependency) {
            throw new TechnicalException(String.format("Dependency for class %s was not found", clazz.getName()));
        }

        return clazz.cast(dependency);
    }

    protected final <T> void addDependency(Class<T> clazz, T dependency) {

        dependenciesMap.put(clazz, dependency);
    }

    public final void init() {

        if (initialized) {
            return;
        }
        initialized = true;
        initDependencies();
    }

    protected abstract void initDependencies();
}
