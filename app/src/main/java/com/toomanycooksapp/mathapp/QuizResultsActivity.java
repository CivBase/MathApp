package com.toomanycooksapp.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class QuizResultsActivity extends ActionBarActivity {

    private QuizSingleton quiz;
    private int lesson;
    private String lessonName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent calledBy = getIntent();
        lesson = calledBy.getExtras().getInt("lesson");
        switch (lesson) {
            //0 -> Addition
            case 0:
                lessonName = "addition";
                break;
            case 1:
                lessonName = "subtraction";
                break;
            case 2:
                lessonName = "multiplication";
                break;
            case 3:
                lessonName = "division";
                break;
            default:
                return;
        }

        quiz = QuizSingleton.getInstance();

        TextView resultsMessage = (TextView) findViewById(R.id.resultsMessage);
        resultsMessage.setText("You answered " + quiz.getNumberCorrect() + " questions correctly out of " + quiz.getNumberOfQuestions() + "!");

        Button homeButton = (Button) findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomeScreen();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_results, menu);
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

    //Returns to MainActivity, deleting itself on exit
    public void goToHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
