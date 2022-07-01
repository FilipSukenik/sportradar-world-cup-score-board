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

    protected DependencyFactory() {

        dependenciesMap = new HashMap<>();
    }

    public final <T> T getDependency(Class<T> clazz) {

        return clazz.cast(dependenciesMap.get(clazz));
    }

    public final <T> void addDependency(Class<T> clazz, T dependency) {

        dependenciesMap.put(clazz, dependency);
    }
}
