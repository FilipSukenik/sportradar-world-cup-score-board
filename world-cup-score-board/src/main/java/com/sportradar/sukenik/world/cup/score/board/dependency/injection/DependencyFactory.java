/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.HashMap;
import java.util.Map;

public abstract class DependencyFactory {

    private final Map<Class<?>, Object> dependenciesMap;

    protected boolean initialized;

    protected DependencyFactory() {

        dependenciesMap = new HashMap<>();
    }

    public DependencyFactory(Map<Class<?>, Object> dependenciesMap) {

        this.dependenciesMap = dependenciesMap;
    }

    public final <T> T getDependency(Class<T> clazz) {

        if (!initialized) {
            throw new IllegalStateException("Dependency factory has not been initialized.");
        }

        return clazz.cast(dependenciesMap.get(clazz));
    }

    public final <T> void addDependency(Class<T> clazz, T dependency) {

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

    public boolean isInitialized() {

        return initialized;
    }
}
