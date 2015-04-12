package com.toomanycooksapp.mathapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends ActionBarActivity {

    Spinner difficultySpinner = null;
    Button additionButton = null;
    Button subtractionButton = null;
    Button multiplicationButton = null;
    Button divisionButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        difficultySpinner = (Spinner) findViewById(R.id.difficultySpinner);
        additionButton = (Button) findViewById(R.id.additionButton);
        subtractionButton = (Button) findViewById(R.id.subtractionButton);
        multiplicationButton = (Button) findViewById(R.id.multiplicationButton);
        divisionButton = (Button) findViewById(R.id.divisionButton);

        difficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    NumberGenerator.setLimit(11);
                } else if (position == 1) {
                    NumberGenerator.setLimit(100);
                } else if (position == 2) {
                    NumberGenerator.setLimit(1000);
                }
                Log.d("Msg", "Random value: " + NumberGenerator.generateNumber());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson(0);
            }
        });
        subtractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson(1);
            }
        });
        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson(2);
            }
        });
        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLesson(3);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // Method to move to the lesson activity
    // This method will be called by all buttons, the difference is the integer passed in.
    // All buttons will lead to the same activity, but the int will be passed to the LessonActivity
    // Aspects of the LessonActivity will change depending on which int is passed (i.e. what button is clicked)
    // For instance if the Addition button is clicked, 0 is passed, the app will go to the LessonActivity and have the game, flash cards, etc.
    // which correspond to the Addition Lesson
    // (if this doesn't make sense let me know and I will try to explain it better)
    // (if it does make sense but you think there is a better way to do it go for it)
    private void goToLesson(int lesson) {
        Intent lessonIntent = new Intent(this, MathActivity.class);
        lessonIntent.putExtra("lesson", lesson);
        startActivity(lessonIntent);
    }
}
