package com.cicnp.rgtech.cicnpv02.WelcomeScreen;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cicnp.rgtech.cicnpv02.MainActivity;
import com.cicnp.rgtech.cicnpv02.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    //Dots
    List<ImageView> dotImageArray;
    LinearLayout linearLayout_dotImage;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ViewPagerAdapter mViewPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getSharedPreferences(getString(R.string.shared_preferences_key), MODE_PRIVATE).getBoolean("LoggedIn", true))
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        setContentView(R.layout.activity_welcome);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new Welcome1Fragment());
        mViewPagerAdapter.addFragment(new Welcome2Fragment());
        mViewPagerAdapter.addFragment(new WelcomeLoginFragment());


        dotImageArray = new ArrayList<>();
        linearLayout_dotImage = (LinearLayout) findViewById(R.id.welcome_linearLayout_dots);
        for(int i=0; i<mViewPagerAdapter.mFragmentList.size(); i++)
        {
            addImageDot();
        }
        dotImageArray.get(0).setImageResource(R.drawable.dot_selected);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);

        mViewPager.addOnPageChangeListener(this);

    }


    //ViewPager Dots

    public void addImageDot()
    {
        ImageView imageView = new ImageView(WelcomeActivity.this);
        imageView.setBackgroundResource(R.drawable.dot);
        imageView.setLayoutParams(new ViewGroup.LayoutParams((int) getResources().getDimension(R.dimen.welcome_dot_size), (int) getResources().getDimension(R.dimen.welcome_dot_size)));

        dotImageArray.add(imageView);
        linearLayout_dotImage.addView(imageView);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for(int i = 0; i<dotImageArray.size(); i++)
        {
            if(i == position)
                dotImageArray.get(i).setImageResource(R.drawable.dot_selected);
            else
                dotImageArray.get(i).setImageResource(R.drawable.dot);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }





    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }

}
