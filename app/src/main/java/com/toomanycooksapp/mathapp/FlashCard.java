package com.toomanycooksapp.mathapp;


public class FlashCard {
    private Problem prob;

    public FlashCard(Problem p) {
        this.prob = p;
    }

    public String questionView() {
        return prob.getQuestion();
    }

    public String answerView() {
        return prob.getAnswer(0);
    }
}
