package com.toomanycooksapp.mathapp;

import java.util.Random;


public class MultiplicationProblemBuilder implements ProblemBuilder {
    private static Random rand = new Random();

    public MultiplicationProblemBuilder() {
        // do nothing
    }

    @Override
    public Problem buildFlashCardProblem() {
        Problem p = new Problem(Problem.FLASH_CARD);

        int op1 = NumberGenerator.generateNumber();
        int op2 = NumberGenerator.generateNumber();

        p.setQuestion(buildQuestionString(op1, op2));
        p.setAnswer(0, buildCorrectAnswer(op1, op2));
        p.setCorrectIndex(0);

        return p;
    }

    @Override
    public Problem buildQuizProblem() {
        Problem p = new Problem(Problem.QUIZ);

        int op1 = NumberGenerator.generateNumber();
        int op2 = NumberGenerator.generateNumber();

        p.setQuestion(buildQuestionString(op1, op2));

        int correctIndex = rand.nextInt(4);
        p.setCorrectIndex(correctIndex);
        p.setAnswer(correctIndex, buildCorrectAnswer(op1, op2));
        for (int i = 0; i < 4; i++) {
            if (i != correctIndex) {
                p.setAnswer(i, buildIncorrectAnswer(op1, op2));
            }
        }

        return p;
    }

    private String buildQuestionString(int op1, int op2) {
        return "What is " + op1 + " x " + op2 + "?";
    }

    private String buildCorrectAnswer(int op1, int op2) {
        return "" + (op1 * op2);
    }

    private String buildIncorrectAnswer(int op1, int op2) {
        int correctAnswer = op1 * op2;
        int output = correctAnswer;

        while (output == correctAnswer) {
            output = NumberGenerator.generateNumber() * NumberGenerator.generateNumber();
        }

        return "" + output;
    }
}
