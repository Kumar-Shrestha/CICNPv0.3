package com.cicnp.rgtech.cicnpv02.SearchBlacklist;


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
public class SearchBlackListFragment extends Fragment implements View.OnClickListener {

    View view;

    EditText editText_search;
    Button button_search;

    public SearchBlackListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search_black_list, container, false);

        editText_search = (EditText) view.findViewById(R.id.searchBlackList_editText_search);

        button_search = (Button) view.findViewById(R.id.searchBlacklist_button_search);
        button_search.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.searchBlacklist_button_search:



                break;
        }
    }
}
