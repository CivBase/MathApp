package com.toomanycooksapp.mathapp;

/**
 * Created by Zachary Bales on 2/22/2015.
 */
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
