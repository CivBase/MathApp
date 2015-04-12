package com.toomanycooksapp.mathapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by david on 3/30/15.
 */
public class DrawSubtractionGame extends View {

    private int goal;
    private int[] tiles = new int[5];
    private int tilesToReachGoal;
    private int score = 0;
    private boolean newGoal = true;
    private int canvasHeight;
    private int canvasWidth;

    public DrawSubtractionGame(Context context) {
        super(context);
    }


    //create random goal number from 4 - 15
    private void createGoal() {
        Random r = new Random();
        goal = r.nextInt(15 - 4 + 1) + 4;
    }

    // produces random number between 2 and 4
    // This number is the number of tiles that add up to product the goal
    private void createTilesToReachGoal() {
        Random r = new Random();
        tilesToReachGoal = r.nextInt(4 - 2 + 1) + 2;
    }

    // Produces the tile values
    // There will be tilesToReachGoal number of tiles that add up to the goal
    // the rest will be random
    // ex. if tilesToReachGoal = 3 and the goal = 9 three of the tiles would be
    // something like 3, 5, 1 which add up to the goal and the other two would be
    // random
    private void createTiles() {
        int tempGoal = goal;
        int curTotal = 0;

        for (int i = 0; i < tilesToReachGoal - 1; i++) {
            Random r = new Random();
            tiles[i] = r.nextInt((tempGoal - (tilesToReachGoal - i)) - 1 + 1) + 1;
            tempGoal -= tiles[i];
            curTotal += tiles[i];

            Log.d("tiles", "i: " + i + " tiles[i]: " + tiles[i] + " tempGoal: " + tempGoal + " goal: " + goal + " tilesToReachGoal: " + tilesToReachGoal + " curTotal: " + curTotal + " \n");
        }

        tiles[tilesToReachGoal - 1] = goal - curTotal;


        for (int i = tilesToReachGoal; i < 5; i++) {
            Random r = new Random();
            tiles[i] = r.nextInt(14 - 1 + 1) + 1;
        }
    }

    // Shuffles tiles array
    private void shuffleArray() {
        Random rnd = new Random();
        for (int i = tiles.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = tiles[index];
            tiles[index] = tiles[i];
            tiles[i] = a;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (newGoal) {
            createGoal();
            createTilesToReachGoal();
            createTiles();
            shuffleArray();
            newGoal = false;
        }

        int checkCorrect = 0;
        for (int i = 0; i < 5; i++) {
            checkCorrect += tiles[i];
        }

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        Paint borderBlue = new Paint();
        borderBlue.setColor(Color.BLUE);
        borderBlue.setStyle(Paint.Style.STROKE);

        Paint fillBlueText = new Paint();
        fillBlueText.setColor(Color.BLUE);
        fillBlueText.setStyle(Paint.Style.FILL);
        fillBlueText.setTextSize(100);

        Paint fillGreenText = new Paint();
        fillGreenText.setColor(Color.parseColor("#19BF19"));
        fillGreenText.setStyle(Paint.Style.FILL);
        fillGreenText.setTextSize(100);

        Paint fillRedText = new Paint();
        fillRedText.setColor(Color.RED);
        fillRedText.setStyle(Paint.Style.FILL);
        fillRedText.setTextSize(100);

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != 0) {
                canvas.drawText(String.valueOf(tiles[i]), (canvas.getWidth() / 6) * (i + 1), (canvas.getHeight() / 2), fillBlueText);
            }
        }
        canvas.drawText("GOAL: ", (canvas.getWidth() / 4) - 100, canvas.getHeight() / 4, fillBlueText);
        canvas.drawText(String.valueOf(goal), (canvas.getWidth() / 2), (canvas.getHeight() / 4), fillBlueText);

        canvas.drawText("SCORE: ", (canvas.getWidth() / 4) - 100, canvas.getHeight(), fillBlueText);
        canvas.drawText(String.valueOf(score), (canvas.getWidth() / 2), canvas.getHeight(), fillBlueText);

        if (checkCorrect == goal) {
            score += 10;
            newGoal = true;
            canvas.drawText("CORRECT", (canvas.getWidth() / 2) - 225, (canvas.getHeight() / 2) - 200, fillGreenText);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, 500);

        }
        if (checkCorrect < goal) {
            canvas.drawText("INCORRECT", (canvas.getWidth() / 2) - 225, (canvas.getHeight() / 2) - 200, fillRedText);
            canvas.drawText("GAME OVER", (canvas.getWidth() / 2) - 225, (canvas.getHeight() / 2) + 200, fillRedText);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("Touched: ", "Has been touched");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("Up Touch: ", "Up touched");
                float tapXSpace = event.getX();
                float tapYSpace = event.getY();

                if (tapYSpace <= ((canvasHeight / 2) + 100) && tapYSpace >= ((canvasHeight / 2) - 100)) {
                    for (int i = 0; i < 5; i++) {
                        if (tapXSpace > ((canvasWidth / 6) * (i + 1) - 100) && tapXSpace < ((canvasWidth / 6) * (i + 1) + 100)) {
                            tiles[i] = 0;
                        }
                    }
                    invalidate();
                    return true;
                }
            default:
                Log.d("Is here at least: ", String.valueOf(event.getAction()));
                return super.onTouchEvent(event);

        }
    }
}
