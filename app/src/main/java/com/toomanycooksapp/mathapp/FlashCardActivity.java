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

import java.util.ArrayList;


public class FlashCardActivity extends ActionBarActivity {
    private ArrayList<FlashCard> fc;
    private int currentIndex;
    private boolean questionDisplayed;
    private ProblemBuilder pb;
    private TextView flashCardView;

    private Button flipButton;
    private Button backButton;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fc = new ArrayList<FlashCard>();

        Intent calledBy = getIntent();
        int lesson = calledBy.getExtras().getInt("lesson");
        switch (lesson) {
            // 0 -> addition
            case 0:
                pb = new AdditionProblemBuilder();
                break;
            // 1 -> subtraction
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

        flipButton = (Button) findViewById(R.id.flipButton);
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipButton();
            }
        });

        backButton = (Button) findViewById(R.id.previousButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton();
            }
        });

        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton();
            }
        });

        fc.add(new FlashCard(pb.buildFlashCardProblem()));
        flashCardView = (TextView) findViewById(R.id.flashCardView);
        flashCardView.setText(fc.get(0).questionView());
        currentIndex = 0;
        questionDisplayed = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu
        // this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_flash_card, menu);
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

    public void flipButton() {
        if (questionDisplayed) {
            flashCardView.setText(fc.get(currentIndex).answerView());
            questionDisplayed = false;
        } else {
            flashCardView.setText(fc.get(currentIndex).questionView());
            questionDisplayed = true;
        }
    }

    public void backButton() {
        // TODO: make this go to previous card if one exists
        if (currentIndex == 0) {
            // TODO: pop up that alerts the user that there are no more previous cards
            return;
        }

        currentIndex--;
        flashCardView.setText(fc.get(currentIndex).questionView());
        questionDisplayed = true;
    }

    public void nextButton() {
        // TODO: make this go to the next card in the array, creating one if necessary
        if (currentIndex == fc.size() - 1) {
            fc.add(new FlashCard(pb.buildFlashCardProblem()));
        }

        currentIndex++;
        flashCardView.setText(fc.get(currentIndex).questionView());
        questionDisplayed = true;
    }
}
