package com.toomanycooksapp.mathapp.lessons;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toomanycooksapp.mathapp.R;

import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class PictureFragment extends Fragment {


    private int subject;
    private int pass;
    private LinearLayout top;
    private LinearLayout bottom;
    private View vthis;
    private final static Random RANDOM = new Random(System.currentTimeMillis());
    public static final int NUMBERS[][] = {
            /*ADD**/{1, 2, 3, 4, 5},
            /*SUB**/{1, 2, 3, 4, 5, 6, 7, 8},
            /*MULT*/{2, 3, 4},
            /*DIV**/{6, 8, 9, 12}
    };
    private static final int[][] DIVISORS = {
            /*6*/{2, 3},
            /*8**/{2, 4},
            /*9**/{3},
            /*12*/{2, 3, 4},
    };
    public static int answer = -1;

    public PictureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vthis = inflater.inflate(R.layout.fragment_picture, container, false);
        top = (LinearLayout) vthis.findViewById(R.id.picture_x_layout);
        bottom = (LinearLayout) vthis.findViewById(R.id.picture_y_layout);
        Bundle b = getArguments();
        subject = b.getInt("subject");
        pass = b.getInt("pass");
        switch (subject) {
            case 0:
                add();
                break;
            case 1:
                sub();
                break;

            case 2:
                mult();
                break;
            case 3:
                div();
                break;

        }


        return vthis;
    }

    private void add() {
        int x = NUMBERS[subject][RANDOM.nextInt(NUMBERS[subject].length)];
        int y = NUMBERS[subject][RANDOM.nextInt(NUMBERS[subject].length)];
        ((TextView) vthis.findViewById(R.id.picture_x)).setText("" + x);
        ((TextView) vthis.findViewById(R.id.picture_y)).setText("" + y);

        for (int i = 0; i < x; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);
            square.setImageResource(R.drawable.square_green);
            top.addView(square);
        }
        for (int i = 0; i < y; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);

            square.setImageResource(R.drawable.square_green);
            bottom.addView(square);
        }

        answer = x + y;


    }

    private void sub() {
        int idx = RANDOM.nextInt(NUMBERS[subject].length);
        int x = NUMBERS[subject][idx];
        int y = NUMBERS[subject][RANDOM.nextInt(idx + 1)];

        ((TextView) vthis.findViewById(R.id.picture_x)).setText("" + x);
        ((TextView) vthis.findViewById(R.id.picture_y)).setText("" + y);
        ((TextView) vthis.findViewById(R.id.picture_subject)).setText(DefinitionFragment.DEFINITIONS[subject] + " ");
        ((TextView) vthis.findViewById(R.id.picture_lable2)).setText("");
        ((TextView) vthis.findViewById(R.id.picture_question)).setText("How many squares are left?");
        answer = x - y;
        for (int i = 0; i < x; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);
            square.setImageResource(R.drawable.square_green);
            top.addView(square);
        }
        for (int i = 0; i < y; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);

            square.setImageResource(R.drawable.square_green);
            bottom.addView(square);
        }


    }

    private void div() {
        int index = RANDOM.nextInt(NUMBERS[subject].length);
        int x = NUMBERS[subject][index];
        int y = DIVISORS[index][RANDOM.nextInt(DIVISORS[index].length)];


        ((TextView) vthis.findViewById(R.id.picture_x)).setText("" + x);
        ((TextView) vthis.findViewById(R.id.picture_y)).setText("" + y);
        ((TextView) vthis.findViewById(R.id.picture_subject)).setText(DefinitionFragment.DEFINITIONS[subject] + " ");
        ((TextView) vthis.findViewById(R.id.picture_into)).setText(" into ");
        ((TextView) vthis.findViewById(R.id.picture_lable2)).setText(" groups");
        ((TextView) vthis.findViewById(R.id.picture_question)).setText("How many squares are in each group?");
        answer = x / y;
        for (int i = 0; i < x; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);
            square.setImageResource(R.drawable.square_green);
            top.addView(square);
        }

        for (int i = 0; i < y; i++) {
            LinearLayout group = new LinearLayout(getActivity());
            group.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < answer; j++) {
                ImageView square = new ImageView(getActivity());
                square.setPadding(5, 5, 5, 5);
                square.setImageResource(R.drawable.square_green);
                group.addView(square);
            }
            bottom.setDividerPadding(5);
            group.setPadding(20, 0, 20, 0);
            bottom.addView(group);
        }
    }


    private void mult() {
        int x = NUMBERS[subject][RANDOM.nextInt(NUMBERS[subject].length)];
        int y = NUMBERS[subject][RANDOM.nextInt(NUMBERS[subject].length)];

        ((TextView) vthis.findViewById(R.id.picture_x)).setText("" + x);
        ((TextView) vthis.findViewById(R.id.picture_y)).setText("" + y);
        ((TextView) vthis.findViewById(R.id.picture_subject)).setText(DefinitionFragment.DEFINITIONS[subject] + " ");
        ((TextView) vthis.findViewById(R.id.picture_lable2)).setText("");
        ((TextView) vthis.findViewById(R.id.picture_question)).setText("How many squares are there total?");
        answer = x * y;
        for (int i = 0; i < x; i++) {
            ImageView square = new ImageView(getActivity());
            square.setPadding(5, 5, 5, 5);
            square.setImageResource(R.drawable.square_green);
            top.addView(square);
        }
        bottom.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < y; i++) {
            LinearLayout row = new LinearLayout(getActivity());
            row.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < x; j++) {
                ImageView square = new ImageView(getActivity());
                square.setPadding(5, 5, 5, 5);
                square.setImageResource(R.drawable.square_green);
                row.addView(square);
            }
            bottom.setDividerPadding(5);
            row.setPadding(0, 20, 0, 20);
            bottom.addView(row);
        }
    }

}
