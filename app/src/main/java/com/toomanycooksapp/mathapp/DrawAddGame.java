package com.toomanycooksapp.mathapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Stack;

/**
 * Created by david on 2/18/15.
 * Creates view for the addition game
 */
public class DrawAddGame extends View{

    private int[] fills = new int[49];
    private boolean changed = false;
    private boolean isUpdating = false;
    private int goal;
    private int numToDrop;
    private int squareSize;
    private boolean gameOver = false;
    private int score = 0;
    private int[] dropQueue = new int[3];
    private boolean newGame = true;


    public DrawAddGame(Context context) {
        super(context);
    }

    private void setGoal() {
        Random r = new Random();
        goal = r.nextInt(18 - 10 + 1) + 10;
    }

    private void setNumToDrop() {
        Random r = new Random();
        int tempDrop = r.nextInt(9 - 1 + 1) + 1;
        numToDrop = dropQueue[0];
        dropQueue[0] = dropQueue[1];
        dropQueue[1] = dropQueue[2];
        dropQueue[2] = tempDrop;
    }

    private void initDropQueue() {
        Random r = new Random();
        for(int i = 0; i < 3; i++) {
            dropQueue[i] = r.nextInt(9 - 1 + 1) + 1;
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(newGame) {
            initDropQueue();
            newGame = false;
        }

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

        if(gameOver) {
            canvas.drawText("GAMEOVER", (canvas.getWidth() / 4), (canvas.getHeight() / 2), fillBlueText);
            canvas.drawText("SCORE", (canvas.getWidth() / 4) + 100, (canvas.getHeight() / 2) + 150, fillBlueText);
            canvas.drawText("_______", (canvas.getWidth() / 4) + 100, (canvas.getHeight() / 2) + 150, fillBlueText);
            canvas.drawText(String.valueOf(score),(canvas.getWidth() / 2) - 50, (canvas.getHeight() / 2) + 250, fillBlueText);
        }
        else {
            isUpdating = false;

            if (!changed) {
                setGoal();
                changed = true;
            }

            squareSize = canvas.getWidth() / 9;
            int x = squareSize;
            int y = canvas.getHeight() - (9 * squareSize);
            int xEnd = canvas.getWidth() - squareSize - 1;


            Rect[] squares = new Rect[49];
            for (int i = 0; i < squares.length; i++) {
                squares[i] = new Rect();
                squares[i].set(x, y, (x + squareSize), (y + squareSize));

                canvas.drawRect(squares[i], borderBlue);
                if (fills[i] != 0) {
                    canvas.drawText(String.valueOf(fills[i]), (x + (squareSize / 4)), (y + squareSize - 20), fillBlueText);
                }

                x += squareSize;
                if (x >= xEnd) {
                    x = squareSize;
                    y += squareSize;
                }
            }

            Rect goalText = new Rect();
            Rect scoreText = new Rect();

            goalText.set(squareSize, squareSize, squareSize + 300, squareSize + 100);
            canvas.drawRect(goalText, borderBlue);
            canvas.drawText("GOAL", (squareSize + (squareSize / 4)), (squareSize + squareSize - 20), fillBlueText);
            canvas.drawText(String.valueOf(goal), squareSize + (squareSize / 4), (squareSize * 2 + squareSize - 20), fillBlueText);

            scoreText.set(squareSize + 400, squareSize, squareSize + 800, squareSize + 100);
            canvas.drawRect(scoreText, borderBlue);
            canvas.drawText("SCORE", (squareSize + 400 + (squareSize / 4)), (squareSize + squareSize - 20), fillBlueText);
            canvas.drawText(String.valueOf(score), squareSize + 400 + (squareSize / 4), (squareSize * 2 + squareSize - 20), fillBlueText);

            // Draw goalqueue
            Rect queue[] = new Rect[3];




            // If number has empty space below it, moves the number down and redraws the canvas
            for (int i = 41; i >= 0; i--) {
                if (fills[i + 7] == 0 && fills[i] != 0) {
                    fills[i + 7] = fills[i];
                    fills[i] = 0;
                    isUpdating = true;
                }
            }
            if (isUpdating) {
                try {
                    for (int i = 0; i < 3; i++) {
                        //            Log.d("Square", "Square " + i + ": " + fills[i]);
                        queue[i] = new Rect();
                        queue[i].set(squareSize + (squareSize * i), canvas.getHeight() - squareSize, squareSize + (squareSize * i) + squareSize, canvas.getHeight());

                        canvas.drawRect(queue[i], borderBlue);
                        if (dropQueue[i] != 0) {
                            canvas.drawText(String.valueOf(dropQueue[i]), (squareSize + (squareSize * i) + (squareSize / 4)), (canvas.getHeight() - squareSize + squareSize - 20), fillBlueText);
                        }
                    }
                    Thread.sleep(200);
                } catch (InterruptedException e) {

                }
                invalidate();
            } else {
                setNumToDrop();
                // Draw goalqueue

                for (int i = 0; i < 3; i++) {
                    //            Log.d("Square", "Square " + i + ": " + fills[i]);
                    queue[i] = new Rect();
                    queue[i].set(squareSize + (squareSize * i), canvas.getHeight() - squareSize, squareSize + (squareSize * i) + squareSize, canvas.getHeight());

                    canvas.drawRect(queue[i], borderBlue);
                    if (dropQueue[i] != 0) {
                        canvas.drawText(String.valueOf(dropQueue[i]), (squareSize + (squareSize * i) + (squareSize / 4)), (canvas.getHeight() - squareSize + squareSize - 20), fillBlueText);
                    }
                }
                Log.d("drop nums", "num: " + numToDrop + " 1: " + dropQueue[0] + " 2: " + dropQueue[1] + " 3: " + dropQueue[2]);
                Rect toDropRect = new Rect();
                toDropRect.set((4 * squareSize), canvas.getHeight() / 4, 5 * squareSize, (canvas.getHeight() / 4) + squareSize);
                canvas.drawRect(toDropRect, borderBlue);

                canvas.drawText(String.valueOf(numToDrop), ((4 * squareSize) + (squareSize / 4)), ((canvas.getHeight() / 4) + squareSize - 20), fillBlueText);

                int startPos = 0;
                for(int i = 0; i < 48; i++) {
                    Stack<Integer> toRemove = new Stack();
                    int curPos = i;
                    int curTotal = 0;
                    if(fills[i] != 0) {
                        while (curPos != startPos * 7 && fills[curPos] != 0) {
                            if(i != 0 && i != 7 && i != 14 && i != 21 && i != 28 && i != 35 && i != 42) {
                                if(fills[i - 1] != 0) {
                                    break;
                                }
                            }
                            curTotal += fills[curPos];
                            toRemove.push(curPos);
                            curPos++;
                            if(curPos > 48) {
                                break;
                            }
                        }
                        if(curTotal == goal) {
                            while(!toRemove.empty()){
                                fills[toRemove.pop()] = 0;
                                score += 10;
                            }
                            setGoal();
                            canvas.drawText("GOAL", (canvas.getWidth() / 2) - 225, (canvas.getHeight() / 2) - 200, fillGreenText);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    invalidate();
                                }
                            }, 300);
                            break;
                        }
                        while(!toRemove.empty()){
                            toRemove.pop();
                        }
                        curPos = i;
                        curTotal = 0;
                        while (curPos != startPos + 41 && fills[curPos] != 0) {
                            curTotal += fills[curPos];
                            toRemove.push(curPos);

                            curPos += 7;
                            if(curPos > 48) {
                                break;
                            }
                        }
                        if(curTotal == goal) {
                            while(!toRemove.empty()){
                                fills[toRemove.pop()] = 0;
                                score += 10;
                            }
                            setGoal();
                            canvas.drawText("GOAL", (canvas.getWidth() / 2) - 225, (canvas.getHeight() / 2) - 200, fillGreenText);
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    invalidate();
                                }
                            }, 300);
                            break;
                        }
                    }
                    startPos++;
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float tapSpace = event.getX();
                int idx;
                if (tapSpace < 2 * squareSize) {
                    idx = 0;
                } else if (tapSpace < 3 * squareSize) {
                    idx = 1;
                } else if (tapSpace < 4 * squareSize) {
                    idx = 2;
                } else if (tapSpace < 5 * squareSize) {
                    idx = 3;
                } else if (tapSpace < 6 * squareSize) {
                    idx = 4;
                } else if (tapSpace < 7 * squareSize) {
                    idx = 5;
                } else {
                    idx = 6;
                }

                if(fills[idx] != 0) {
                    gameOver = true;
                }
                else {
                    fills[idx] = numToDrop;
                }
                invalidate();
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
