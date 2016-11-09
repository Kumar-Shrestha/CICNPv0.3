package com.cicnp.rgtech.cicnpv02.AddBlacklist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cicnp.rgtech.cicnpv02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlackListName extends Fragment implements View.OnClickListener {

    //View
    View view;

    //TextView
    TextView textView_blackListFirstName;
    TextView textView_blackListMiddleName;
    TextView textView_blackListLastName;

    Button next;

    public BlackListName() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_black_list_fragment_one, container, false);

        next = (Button) view.findViewById(R.id.addBlacklist_button_next);
        next.setOnClickListener(this);

        textView_blackListFirstName = (TextView) view.findViewById(R.id.addBlackList_editText_firstName);
        textView_blackListMiddleName = (TextView) view.findViewById(R.id.addBlackList_editText_middleName);
        textView_blackListLastName = (TextView) view.findViewById(R.id.addBlackList_editText_lastName);

        if(BlackListDetails.firstName != null)
        {
            textView_blackListFirstName.setText(BlackListDetails.firstName);
        }

        if(BlackListDetails.middleName != null)
        {
            textView_blackListMiddleName.setText(BlackListDetails.middleName);
        }

        if(BlackListDetails.lastName != null)
        {
            textView_blackListFirstName.setText(BlackListDetails.lastName);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addBlacklist_button_next:
                BlackListDetails.firstName = textView_blackListFirstName.getText().toString();
                BlackListDetails.middleName = textView_blackListMiddleName.getText().toString();
                BlackListDetails.lastName = textView_blackListLastName.getText().toString();

                AddressDOB addressDOB = new AddressDOB();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addBlackList_container, addressDOB);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
