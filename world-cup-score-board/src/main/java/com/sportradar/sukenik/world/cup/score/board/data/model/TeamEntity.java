/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.data.model;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;

/**
 * Represents data entity for Team that contains name of the team and score. This entity has restrictions for name (cannot be null nor empty)
 * and for score (cannot be negative). This is ensured in every constructor and setter that exists, however, default constructor is also
 * provided in, because all entity classes should Java beans (construction can be handled by any framework which may need default constructor).
 */
public class TeamEntity {

    private String name;

    private int score;

    public static final String INCORRECT_NAME_MESSAGE = "name should not be null nor empty string";

    public static final String INCORRECT_SCORE_MESSAGE = "score cannot be negative value";

    /**
     * Default constructor so {@link TeamEntity} is Java Bean.
     */
    public TeamEntity() {

    }

    public TeamEntity(@NotNull String name) {

        Assert.hasText(name, INCORRECT_NAME_MESSAGE);

        this.name = name;
    }

    public TeamEntity(@NotNull String name, int score) {

        this(name);

        Assert.isTrue(score >= 0, INCORRECT_SCORE_MESSAGE);

        this.score = score;
    }

    public TeamEntity(@NotNull TeamEntity toCopy) {

        Assert.notNull(toCopy, "Cannot create copy of null TeamEntity");
        Assert.hasText(toCopy.getName(), INCORRECT_NAME_MESSAGE);

        this.name = toCopy.getName();
        this.score = toCopy.getScore();
    }

    public String getName() {

        return name;
    }

    public int getScore() {

        return score;
    }

    /**
     * Sets score.
     * @param score non negative number.
     * @throws IllegalArgumentException in case that score is negative number.
     */
    public void setScore(int score) {

        Assert.isTrue(score >= 0, INCORRECT_SCORE_MESSAGE);

        this.score = score;
    }

    /**
     * Sets name of the team.
     * @param name non-null, nor empty string.
     */
    public void setName(@NotNull String name) {

        Assert.hasText(name, INCORRECT_NAME_MESSAGE);

        this.name = name;
    }
}
