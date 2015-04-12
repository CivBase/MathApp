package com.toomanycooksapp.mathapp;

/**
 * Created by Zachary Bales on 2/22/2015.
 */
public class Problem {
    public static final int FLASH_CARD = 0;
    public static final int QUIZ = 1;

    private String question;
    private String[] answers;
    private int correctIndex;

    public Problem(int setting) {
        switch (setting) {
            case FLASH_CARD:
                this.answers = new String[1];
                this.correctIndex = 0;
                break;
            case QUIZ:
                this.answers = new String[4];
                break;
        }
    }

    public void setQuestion(String input) {
        this.question = input;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setAnswer(int index, String input) {
        this.answers[index] = input;
    }

    public String getAnswer(int index) {
        return this.answers[index];
    }

    public void setCorrectIndex(int input) {
        this.correctIndex = input;
    }

    public int getCorrectIndex() {
        return this.correctIndex;
    }

    public int getAnswerCount() {
        return this.answers.length;
    }
}