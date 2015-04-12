package com.toomanycooksapp.mathapp;

/**
 * Created by Zachary Bales on 2/25/2015.
 */
public class QuizQuestion {
    private Problem prob;

    public QuizQuestion(Problem p) {
        this.prob = p;
    }

    public String questionView() {
        return this.prob.getQuestion();
    }

    public String answerView(int selection) {
        return this.prob.getAnswer(selection);
    }

    public boolean isTrue(int index) {
        return index == prob.getCorrectIndex();
    }
}
