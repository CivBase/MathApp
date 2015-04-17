package com.toomanycooksapp.mathapp.lessons;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.toomanycooksapp.mathapp.Popup;
import com.toomanycooksapp.mathapp.R;


public class LessonsActivity extends ActionBarActivity implements View.OnClickListener {
    public static final String[] SUBJECTS = {"ADD", "SUBTRACT", "MULTIPLY", "DIVIDE"};

    private Fragment current;
    private FragmentManager manager;

    private int pass = 0;
    private int subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        manager = this.getFragmentManager();
        subject = getIntent().getIntExtra("subject", 0);
        addFragment(DefinitionFragment.class.getName());
    }

    private void addFragment(String name) {
        Fragment fragment = Fragment.instantiate(this, name);
        Bundle b = new Bundle();
        b.putInt("pass", pass);
        b.putInt("subject", subject);
        fragment.setArguments(b);
        manager.executePendingTransactions();
        invalidateOptionsMenu();

        manager.beginTransaction()
            .replace(R.id.lesson_content, fragment)
            .commit();

        current = fragment;
    }

    // handle all transactions and events here
    @Override
    public void onClick(View v) {
        switch (pass) {
            case 0:
                onDefinitionClicks(v);
                break;
            case 1:
                onPictureClicks(v);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                onProblemClicks(v);
                break;
        }
    }

    private void onProblemClicks(View view) {
        if (view.getId() == R.id.problem_submit) {
            boolean passed = true;
            for (int i = 0; i < 4; i++) {
                if (ProblemFragment.ANSWERKEYS[i] != ProblemFragment.ANSWERSGIVEN[i]) {
                    passed = false;
                    break;
                }
            }

            if (passed) {
                pass++;
            }

            String title = passed ? "Great Job!" : "Not Quite";
            if (pass < 5) {
                String buttonText;
                String message;
                if (passed) {
                    buttonText = "Next Question!";
                    message = "Well done! Get " + (5 - pass) +
                        " more right and you finish the lesson!";
                } else {
                    buttonText = "Try Again";
                    message = "Almost! You can do this! Get " + (5 - pass) +
                        " more right and you finish the lesson!";
                }

                Popup.create(
                    view.getContext(),
                    title,
                    message,
                    buttonText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((ProblemFragment) current).initQuestion(pass);
                        }
                    });

                return;
            }

            Popup.create(
                view.getContext(),
                title,
                "Well done! You passed this lesson!",
                "Onward to next lesson!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((ProblemFragment) current).initQuestion(pass);
                    }
                });
        }
    }

    private void onPictureClicks(View view) {
        if (view.getId() == R.id.picture_submit) {
            int answerGiven = Integer.parseInt(
                "" + ((EditText) findViewById(R.id.picture_answer)).getText());

            int answerKey = PictureFragment.answer;
            int x = Integer.parseInt("" + ((TextView) findViewById(R.id.picture_x)).getText());
            int y = Integer.parseInt("" + ((TextView) findViewById(R.id.picture_y)).getText());
            String equation = x + " " + SUBJECTS[subject] + (subject > 1 ? " " : " by ") + y +
                " equals " + answerKey;

            if (answerGiven == answerKey) {
                Popup.create(
                    view.getContext(),
                    "Great Job!",
                    equation,
                    "Onward!",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pass++;
                            addFragment(ProblemFragment.class.getName());
                        }
                    });

                return;
            }

            Popup.create(
                view.getContext(),
                "Not Quite",
                equation + ", not " + answerGiven,
                "Try Again",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addFragment(PictureFragment.class.getName());
                    }
                });
        }
    }

    private void onDefinitionClicks(View view) {
        int id = view.getId();
        int pressed = -1;
        switch (id) {
            case R.id.define_increase:
                pressed = 0;
                break;
            case R.id.define_repeat:
                pressed = 2;
                break;
            case R.id.define_seperate:
                pressed = 3;
                break;
            case R.id.define_take:
                pressed = 1;
                break;
        }

        String message = "To " + SUBJECTS[pressed] + " means to " +
            DefinitionFragment.DEFINITIONS[pressed] + ".";

        if (subject == pressed) {
            Popup.create(
                view.getContext(),
                "Great Job!",
                message,
                "Onward!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pass++;
                        addFragment(PictureFragment.class.getName());
                    }
                });

            return;
        }

        Popup.create(
            view.getContext(),
            "Not Quite",
            message,
            "Try Again",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });
    }
}
