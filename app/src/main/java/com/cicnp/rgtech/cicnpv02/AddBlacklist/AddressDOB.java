package com.cicnp.rgtech.cicnpv02.AddBlacklist;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.cicnp.rgtech.cicnpv02.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressDOB extends Fragment implements View.OnClickListener {

    View view;

    EditText permanentAddress;
    DatePicker dateOfBirth;

    Button next;

    public AddressDOB() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_address_dob, container, false);

        permanentAddress = (EditText) view.findViewById(R.id.addBlackList_editText_permanentAddress);

        dateOfBirth = (DatePicker) view.findViewById(R.id.addBlackList_datePicker_dateOfBirth);

        next = (Button) view.findViewById(R.id.addBlacklist_button_next);
        next.setOnClickListener(this);

        if(BlackListDetails.permanentAddress != null)
        {
            permanentAddress.setText(BlackListDetails.permanentAddress);
        }

        if(BlackListDetails.birthDay != null
                && BlackListDetails.birthMonth != null
                && BlackListDetails.birthYear != null)
        {
            dateOfBirth.updateDate(Integer.valueOf(BlackListDetails.birthYear),
                    Integer.valueOf(BlackListDetails.birthMonth),
                    Integer.valueOf(BlackListDetails.birthDay));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addBlacklist_button_next:

                BlackListDetails.permanentAddress = permanentAddress.getText().toString();
                BlackListDetails.birthYear = String.valueOf(dateOfBirth.getYear());
                BlackListDetails.birthMonth = String.valueOf(dateOfBirth.getMonth());
                BlackListDetails.birthDay = String.valueOf(dateOfBirth.getDayOfMonth());

                FathersName fathersName = new FathersName();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addBlackList_container, fathersName);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
