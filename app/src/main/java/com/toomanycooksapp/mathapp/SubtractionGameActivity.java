package com.toomanycooksapp.mathapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class SubtractionGameActivity extends ActionBarActivity {
    DrawSubtractionGame sg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction_game);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Popup.create(
            this,
            "HOW TO PLAY",
            "The goal of this game is to remove numbers until all of the remaining numbers add " +
            "up to the GOAL. To remove numbers simply tap on them.",
            "Continue",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            },
            android.R.drawable.ic_dialog_info);

        sg = new DrawSubtractionGame(this);
        setContentView(sg);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate the menu
        // this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_subtraction_game, menu);
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
}
