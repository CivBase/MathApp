package com.toomanycooksapp.mathapp;


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
