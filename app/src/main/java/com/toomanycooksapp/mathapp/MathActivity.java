package com.toomanycooksapp.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.TextView;

import com.toomanycooksapp.mathapp.lessons.LessonsActivity;


public class MathActivity extends ActionBarActivity {
    // this is sent in the intent to the LessonActivity
    // 0 -> addition
    // 1 -> subtraction
    // 2 -> multiplication
    // 3 -> division
    private int lesson;

    Button lessonButton = null;
    Button gameButton = null;
    Button fcButton = null;
    Button quizButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lessonButton = (Button) findViewById(R.id.lessonButton);
        gameButton = (Button) findViewById(R.id.gameButton);
        fcButton = (Button) findViewById(R.id.fcButton);
        quizButton = (Button) findViewById(R.id.quizButton);

        // get called intent and integer corresponding to correct lesson
        Intent calledBy = getIntent();
        lesson = calledBy.getExtras().getInt("lesson");

        TextView lessonText = (TextView) findViewById(R.id.lessonText);
        Button gameButton = (Button) findViewById(R.id.gameButton);
        switch (lesson) {
            case 0:
                lessonText.setText("Addition");
                gameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAddGame();
                    }
                });
                break;
            case 1:
                lessonText.setText("Subtraction");
                gameButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSubtractionGame();
                    }
                });
                break;
            case 2:
                lessonText.setText("Multiplication");
                ((ViewManager) gameButton.getParent()).removeView(gameButton);
                break;
            case 3:
                lessonText.setText("Division");
                ((ViewManager) gameButton.getParent()).removeView(gameButton);
                break;
        }

        fcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlashCard();
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        lessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLesson();
            }
        });

        lessonButton = (Button) findViewById(R.id.lessonButton);
        gameButton = (Button) findViewById(R.id.gameButton);
        fcButton = (Button) findViewById(R.id.fcButton);
        quizButton = (Button) findViewById(R.id.quizButton);
    }

    private void startLesson() {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("subject", lesson);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu
        // this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_math, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle action bar item clicks here
        // the action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // creates intent and starts AddGameActivity
    private void startAddGame() {
        Intent intent = new Intent(this, AddGameActivity.class);
        startActivity(intent);
    }

    // creates intent and starts SubtractionGameActivity
    private void startSubtractionGame() {
        Intent intent = new Intent(this, SubtractionGameActivity.class);
        startActivity(intent);
    }

    // creates intent and starts FlashCardActivity
    private void startFlashCard() {
        Intent intent = new Intent(this, FlashCardActivity.class);
        intent.putExtra("lesson", lesson);
        startActivity(intent);
    }

    // creates intent and starts QuizQuestionActivity
    private void startQuiz() {
        QuizSingleton.getNewInstance();
        Intent intent = new Intent(this, QuizQuestionActivity.class);
        intent.putExtra("lesson", lesson);
        startActivity(intent);
    }
}
