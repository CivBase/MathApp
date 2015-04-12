package com.toomanycooksapp.mathapp.lessons;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.toomanycooksapp.mathapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProblemFragment extends Fragment {

    private int subject;
    private int pass;

    private TextView questionRoot;
    private TextView questionSubject;
    private TextView questionTotal;
    private TextView questionPassed;
    private CheckBox a;
    private CheckBox b;
    private CheckBox c;
    private CheckBox d;

    private Random ran;

    private static final int[][] GOODANSWERS = {
            /*ADD**/{8, 9, 10, 11, 12, 13, 14, 15},
            /*SUB**/{3, 4, 5, 6, 7, 8},
            /*MULT*/{12, 20, 24, 28, 30, 40},
            /*DIV**/{1, 2, 3, 4, 5, 7}

    };

    public static boolean ANSWERSGIVEN[] = {false, false, false, false};
    public static boolean ANSWERKEYS[] = {false, false, false, false};


    public ProblemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_problems, container, false);
        Bundle bundle = getArguments();
        subject = bundle.getInt("subject");
        pass = bundle.getInt("pass");

        ran = new Random(System.currentTimeMillis());

        questionRoot = (TextView) view.findViewById(R.id.problem_root);
        questionSubject = (TextView) view.findViewById(R.id.problems_subject);
        questionTotal = (TextView) view.findViewById(R.id.problem_total);
        questionPassed = (TextView) view.findViewById(R.id.pass);

        questionSubject.setText(LessonsActivity.SUBJECTS[subject]);

        a = (CheckBox) view.findViewById(R.id.checkBox1);
        b = (CheckBox) view.findViewById(R.id.checkBox2);
        c = (CheckBox) view.findViewById(R.id.checkBox3);
        d = (CheckBox) view.findViewById(R.id.checkBox4);

        a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ANSWERSGIVEN[0] = isChecked;
            }
        });
        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ANSWERSGIVEN[1] = isChecked;
            }
        });
        c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ANSWERSGIVEN[2] = isChecked;
            }
        });
        d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ANSWERSGIVEN[3] = isChecked;
            }
        });

        initQuestion(pass);

        return view;
    }

    public void initQuestion(int pass) {


        System.out.println("question inited");
        this.pass = pass;
        questionPassed.setText("" + (pass - 2));

        int total = GOODANSWERS[subject][ran.nextInt(GOODANSWERS[subject].length)];
        questionTotal.setText("" + total);
        generateAnswerKey();
        a.setText(generateEquation(total, ANSWERKEYS[0]));
        b.setText(generateEquation(total, ANSWERKEYS[1]));
        c.setText(generateEquation(total, ANSWERKEYS[2]));
        d.setText(generateEquation(total, ANSWERKEYS[3]));


        a.setChecked(false);
        b.setChecked(false);
        c.setChecked(false);
        d.setChecked(false);


    }

    private void generateAnswerKey() {

        int c = 1 + ran.nextInt(3);
        int a = 0;
        int i = 0;

        ANSWERKEYS[0] = false;
        ANSWERKEYS[1] = false;
        ANSWERKEYS[2] = false;
        ANSWERKEYS[3] = false;
        //try to do a quick random
        while (a < c) {
            boolean answer = ran.nextBoolean();

            if (!ANSWERKEYS[i % 4] && answer) {
                ANSWERKEYS[i % 4] = answer;
                a++;
            }

            i++;
            if (i > 32) {
                break;
            }
        }
        if (a == 0) ANSWERKEYS[ran.nextInt(4)] = true;
    }

    private String generateEquation(int answer, boolean correct) {


        switch (subject) {
            case 0:
                return genAdd(answer, correct);
            case 1:
                return genSub(answer, correct);
            case 2:
                return genMulti(answer, correct);
            case 3:
                return genDiv(answer, correct);
        }


        return "key = " + correct;
    }

    private String genDiv(int answer, boolean correct) {

        int index = ran.nextInt(GOODANSWERS[3].length);
        int dividend = answer * GOODANSWERS[3][index];
        int divisor = GOODANSWERS[3][correct ? index : (ran.nextBoolean() ?
                (index + 1) % GOODANSWERS[3].length :
                (index - 1) % GOODANSWERS[3].length)];


        return dividend + " / " + divisor;
    }

    private String genMulti(int answer, boolean correct) {
        String eq = "";
        ArrayList<Integer> factors = findFactors(answer);
        int b = factors.get(ran.nextInt(factors.size()));
        int c = answer / b;

        if (!correct) {

            if (ran.nextBoolean())
                b = ran.nextBoolean() ? b - 1 : b + 1;
            else
                c = ran.nextBoolean() ? c - 1 : c + 1;
        }

        return b + " * " + c;
    }


    private ArrayList<Integer> findFactors(int answer) {
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 1; i < answer; i++) {
            if (answer % i == 0) {
                factors.add(i);
                System.out.println("findFactors: " + i + "+ " + answer);
            }

        }

        return factors;
    }


    private String genSub(int answer, boolean correct) {
        int a = correct ? answer : ran.nextBoolean() ? answer - 1 : answer + 1;
        int b = 6 + ran.nextInt(8);
        int c = b - a;
        if (c < 0) {
            b = b + Math.abs(c) + 1;
            c = 1;
        }
        return b + " - " + c;
    }

    private String genAdd(int answer, boolean correct) {

        String eq = "";
        int a = correct ? answer : ran.nextBoolean() ? answer - 1 : answer + 1;
        int b = 2 + ran.nextInt(4);
        while (a > b) {
            eq = eq + b + " + ";
            a = a - b;
            b = 2 + ran.nextInt(3);
        }

        eq = eq + a;
        return eq;
    }
}
