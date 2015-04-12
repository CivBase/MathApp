package com.toomanycooksapp.mathapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.AlertDialog.Builder;


public class QuizQuestionActivity extends ActionBarActivity {

    private QuizSingleton quiz;
    private ProblemBuilder pb;
    private QuizQuestion qq;
    private int lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent calledBy = getIntent();
        lesson = calledBy.getExtras().getInt("lesson");
        switch (lesson) {
            //0 -> Addition
            case 0:
                pb = new AdditionProblemBuilder();
                break;
            //1 -> Subtraction
            case 1:
                pb = new SubtractionProblemBuilder();
                break;
            case 2:
                pb = new MultiplicationProblemBuilder();
                break;
            case 3:
                pb = new DivisionProblemBuilder();
                break;
            default:
                return;
        }

        quiz = QuizSingleton.getInstance();
        quiz.increaseCurrentQuestion();

        qq = new QuizQuestion(pb.buildQuizProblem());

        TextView question = (TextView) findViewById(R.id.quizQuestion);
        question.setText(qq.questionView());

        Button[] answer = new Button[4];
        answer[0] = (Button) findViewById(R.id.answer0);
        answer[1] = (Button) findViewById(R.id.answer1);
        answer[2] = (Button) findViewById(R.id.answer2);
        answer[3] = (Button) findViewById(R.id.answer3);

        for (int i = 0; i < 4; i++) {
            answer[i].setText(qq.answerView(i));
            answer[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO Pop up to report correctness
                    int buttonIndex = -1;
                    switch (v.getId()) {
                        case R.id.answer0:
                            buttonIndex = 0;
                            break;
                        case R.id.answer1:
                            buttonIndex = 1;
                            break;
                        case R.id.answer2:
                            buttonIndex = 2;
                            break;
                        case R.id.answer3:
                            buttonIndex = 3;
                            break;
                    }
                    if (qq.isTrue(buttonIndex)) {
                        quiz.answeredQuestion(true);

                        Builder alert = new Builder(v.getContext());
                        alert.setMessage("Correct!");
                        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (quiz.getCurrentQuestion() == quiz.getNumberOfQuestions()) {
                                    goToQuizResults();
                                } else {
                                    goToNextQuestion();
                                }
                            }
                        });
                        AlertDialog popup = alert.create();
                        popup.show();
                    } else {
                        quiz.answeredQuestion(false);

                        Builder alert = new Builder(v.getContext());
                        alert.setMessage("Incorrect!");
                        alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (quiz.getCurrentQuestion() == quiz.getNumberOfQuestions()) {
                                    goToQuizResults();
                                } else {
                                    goToNextQuestion();
                                }
                            }

                        });
                        AlertDialog popup = alert.create();
                        popup.show();
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToQuizResults() {
        Intent intent = new Intent(this, QuizResultsActivity.class);
        intent.putExtra("lesson", lesson);
        startActivity(intent);
        finish();
    }

    //Makes intent for next question and goes to it, deleting itself on exit
    public void goToNextQuestion() {
        Intent intent = new Intent(this, QuizQuestionActivity.class);
        intent.putExtra("lesson", lesson);
        startActivity(intent);
        finish();
    }
}
