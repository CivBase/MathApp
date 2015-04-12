package com.toomanycooksapp.mathapp;

/**
 * Created by Zachary Bales on 2/25/2015.
 */
public class QuizSingleton {
    private static QuizSingleton instance = null;
    private int numberOfQuestions;
    private int currentQuestion;
    private int numberCorrect;

    private QuizSingleton() {
        this.numberOfQuestions = 5;
        this.currentQuestion = 0;
        this.numberCorrect = 0;
    }

    public static QuizSingleton getNewInstance() {
        instance = new QuizSingleton();
        return instance;
    }

    public static QuizSingleton getInstance() {
        if (instance == null) {
            instance = new QuizSingleton();
        }

        return instance;
    }

    public int getNumberOfQuestions() {
        return this.numberOfQuestions;
    }

    public int getCurrentQuestion() {
        return this.currentQuestion;
    }

    public int getNumberCorrect() {
        return this.numberCorrect;
    }

    public void answeredQuestion(boolean answeredCorrectly) {
        numberCorrect += answeredCorrectly ? 1 : 0;
    }

    public void increaseCurrentQuestion() {
        currentQuestion++;
    }
}
