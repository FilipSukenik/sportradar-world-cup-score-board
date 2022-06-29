/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.db.model;

public class TeamEntity {

    private String name;

    private int score;

    /**
     * Default constructor so {@link TeamEntity} is Java Bean.
     */
    public TeamEntity() {

    }

    public TeamEntity(String name) {

        this.name = name;
        score = 0;
    }

    public String getName() {

        return name;
    }

    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public void setName(String name) {

        this.name = name;
    }
}
