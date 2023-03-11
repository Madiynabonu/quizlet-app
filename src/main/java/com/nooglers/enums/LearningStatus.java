package com.nooglers.enums;

public enum LearningStatus {

    NOT_STUDIED(0), LEARNING(5), MASTERED(15);

    private final int score;

    LearningStatus(int score) {
        this.score = score;
    }
}
