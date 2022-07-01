/**
 * Copyright 2022 European Commission. All rights reserved.
 * European Commission PROPRIETARY/CONFIDENTIAL.
 *
 **/
package com.sportradar.sukenik.world.cup.score.board.dto;

public class TeamDto {

    private String name;

    private int score;

    public TeamDto() {

    }

    public TeamDto(String name) {

        this.name = name;
        this.score = 0;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getScore() {

        return score;
    }

    public void setScore(int score) {

        this.score = score;
    }
}
