package com.toomanycooksapp.mathapp.lessons;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toomanycooksapp.mathapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DefinitionFragment extends Fragment {

    public DefinitionFragment() {
    }

    int subject;
    int pass;

    public final static String[] DEFINITIONS = {"INCREASE", "TAKE AWAY", "REPEAT", "SEPARATE"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_definition, container, false);
        Bundle b = getArguments();
        subject = b.getInt("subject");
        pass = b.getInt("pass");
        ((TextView) view.findViewById(R.id.define_subject)).setText(LessonsActivity.SUBJECTS[subject]);


        return view;

    }
}
