package com.cicnp.rgtech.cicnpv02.AddBlacklist;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cicnp.rgtech.cicnpv02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FathersName extends Fragment implements View.OnClickListener {

    View view;

    EditText fatherFirstName;
    EditText fatherMiddleName;
    EditText fatherLastName;

    Button next;

    public FathersName() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fathers_name, container, false);

        fatherFirstName = (EditText) view.findViewById(R.id.addBlackList_editText_fatherFirstName);
        fatherMiddleName = (EditText) view.findViewById(R.id.addBlackList_editText_fatherMiddleName);
        fatherLastName = (EditText) view.findViewById(R.id.addBlackList_editText_fatherLastName);

        next = (Button) view.findViewById(R.id.addBlacklist_button_next);
        next.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addBlacklist_button_next:

                GrandFathersName grandFathersName = new GrandFathersName();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addBlackList_container, grandFathersName);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
