package com.cicnp.rgtech.cicnpv02.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cicnp.rgtech.cicnpv02.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    //View
    View view;

    //Menu
    GridLayout menuGrid;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_main, container, false);

        menuGrid = (GridLayout) view.findViewById(R.id.main_gridLayout_menu);

        //Add items to menu grid
        //Image ID must be unique to separate click event
        addMenuItem(menuGrid, R.drawable.watch, "Watch");
        addMenuItem(menuGrid, R.drawable.add, "Add\nBlacklist");
        addMenuItem(menuGrid, R.drawable.search, "Search\nBlacklist");
        addMenuItem(menuGrid, R.drawable.add, "Watch");

        return view;
    }

    public void addMenuItem(GridLayout parent, int imageID, String text)
    {
        LinearLayout linearLayout = new LinearLayout(parent.getContext());

        linearLayout.setLayoutParams(new ViewGroup.LayoutParams((int)getResources().getDimension(R.dimen.main_icon_size),(int)getResources().getDimension(R.dimen.main_icon_size)));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        Button button = new Button(linearLayout.getContext());
        button.setLayoutParams(new ViewGroup.LayoutParams((int)getResources().getDimension(R.dimen.main_inner_icon_size),(int)getResources().getDimension(R.dimen.main_inner_icon_size)));
        button.setBackgroundResource(imageID);

        TextView textView = new TextView(linearLayout.getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,(int)getResources().getDimension(R.dimen.main_icon_text_margin),0,0);
        textView.setLayoutParams(layoutParams);
        textView.setText(text);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);

        linearLayout.addView(button);
        linearLayout.addView(textView);

        //Set id
        button.setId(imageID);
        textView.setId(imageID);
        linearLayout.setId(imageID);

        button.setOnClickListener(this);
        textView.setOnClickListener(this);
        linearLayout.setOnClickListener(this);

        parent.addView(linearLayout);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.drawable.watch:
                Toast.makeText(getContext(),"One", Toast.LENGTH_SHORT).show();
                startActivity(new Intent("WatchActivity"));
                break;

            case R.drawable.add:
                Toast.makeText(getContext(),"Two", Toast.LENGTH_SHORT).show();
                break;

            case R.drawable.search:
                Toast.makeText(getContext(),"Three", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
