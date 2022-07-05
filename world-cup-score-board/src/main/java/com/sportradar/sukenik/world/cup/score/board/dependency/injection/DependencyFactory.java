package com.sportradar.sukenik.world.cup.score.board.dependency.injection;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

import com.sportradar.sukenik.world.cup.score.board.exception.TechnicalException;

/**
 * Contains store of all dependencies that are needed by library. Those dependencies are stored in {@link Map} where
 * key is class of object and value is instance of the class.
 */
public abstract class DependencyFactory {

    private final Map<Class<?>, Object> dependenciesMap;

    private boolean initialized;

    protected DependencyFactory() {

        dependenciesMap = new HashMap<>();
    }

    /**
     * Adds possibility to create {@link #dependenciesMap} by client.
     * @param dependenciesMap map of all dependencies.
     */
    protected DependencyFactory(@NotNull Map<Class<?>, Object> dependenciesMap) {

        Assert.notNull(dependenciesMap, "dependenciesMap cannot be null");

        this.dependenciesMap = dependenciesMap;
        initialized = true;
    }

    /**
     * Returns store dependency in map. In case that {@link DependencyFactory} is not initialized or the provided class is null
     * throws proper exception.
     * @param clazz of wanted dependency.
     * @param <T> generic that the instance should be.
     * @return found class or throws {@link TechnicalException} when the class was not found.
     */
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

    /**
     * Adds dependency to map
     * @param clazz of the inserted type.
     * @param dependency instance that should be stored for provided class.
     * @param <T> generic type of which the dependency and class are.
     */
    protected final <T> void addDependency(@NotNull Class<T> clazz, @NotNull T dependency) {

        Assert.notNull(clazz, "clazz cannot be null");
        Assert.notNull(dependency, "dependency cannot be null");

        dependenciesMap.put(clazz, dependency);
    }

    /**
     * Calls abstract {@link #initDependencies()} and changes state of {@link #initialized} to {@code true}.
     */
    public final void init() {

        if (initialized) {
            return;
        }
        initialized = true;
        initDependencies();
    }

    protected abstract void initDependencies();
}
