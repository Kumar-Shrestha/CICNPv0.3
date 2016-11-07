package com.cicnp.rgtech.cicnpv02.SearchBlacklist;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cicnp.rgtech.cicnpv02.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBlackListFragment extends Fragment implements View.OnClickListener {

    View view;

    EditText editText_search;
    Button button_search;
    Spinner spinner_criteria;

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

        // Spinner element
        spinner_criteria = (Spinner) view.findViewById(R.id.searchBlackList_spinner_searchCriteria);

        String[] criteria = getResources().getStringArray(R.array.search_criteria);
        List<String> countryList = new ArrayList<String>(Arrays.asList(criteria));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countryList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_criteria.setAdapter(dataAdapter);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.searchBlacklist_button_search:

                SearchResultFragment searchResultFragment = new SearchResultFragment();
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.searchBlackList_container, searchResultFragment);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }
    }
}
