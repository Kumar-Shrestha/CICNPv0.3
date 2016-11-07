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
public class DetailsInfo extends Fragment implements View.OnClickListener {

    View view;

    EditText contactNumber;
    EditText citizenshipNumber;
    EditText citizenshipIssuedPlace;

    Button button;

    public DetailsInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details_info, container, false);

        contactNumber = (EditText) view.findViewById(R.id.addBlackList_editText_contactNumber);
        citizenshipNumber = (EditText) view.findViewById(R.id.addBlackList_editText_citizenshipNumber);
        citizenshipIssuedPlace = (EditText) view.findViewById(R.id.addBlackList_editText_citizenshipIssuedPlace);

        button = (Button) view.findViewById(R.id.addBlacklist_button_next);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.addBlacklist_button_next:

                Photo photo = new Photo();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.addBlackList_container, photo);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
