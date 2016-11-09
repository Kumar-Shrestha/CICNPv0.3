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
public class GrandFathersName extends Fragment implements View.OnClickListener {

    View view;

    EditText grandFatherFirstName;
    EditText grandFatherMiddleName;
    EditText grandFatherLastName;

    Button next;

    public GrandFathersName() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_grand_fathers_name, container, false);

        grandFatherFirstName = (EditText) view.findViewById(R.id.addBlackList_editText_grandfatherFirstName);
        grandFatherMiddleName = (EditText) view.findViewById(R.id.addBlackList_editText_grandfatherMiddleName);
        grandFatherLastName = (EditText) view.findViewById(R.id.addBlackList_editText_grandfatherLastName);

        next = (Button) view.findViewById(R.id.addBlacklist_button_next);
        next.setOnClickListener(this);

        if(BlackListDetails.grandFatherFirstName != null)
        {
            grandFatherFirstName.setText(BlackListDetails.grandFatherFirstName);
        }

        if(BlackListDetails.grandFatherMiddleName != null)
        {
            grandFatherMiddleName.setText(BlackListDetails.grandFatherMiddleName);
        }

        if(BlackListDetails.grandFatherLastName != null)
        {
            grandFatherLastName.setText(BlackListDetails.grandFatherLastName);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addBlacklist_button_next:

                BlackListDetails.grandFatherFirstName = grandFatherFirstName.getText().toString();
                BlackListDetails.grandFatherMiddleName = grandFatherMiddleName.getText().toString();
                BlackListDetails.grandFatherLastName = grandFatherLastName.getText().toString();


                DetailsInfo detailsInfo = new DetailsInfo();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addBlackList_container, detailsInfo);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
