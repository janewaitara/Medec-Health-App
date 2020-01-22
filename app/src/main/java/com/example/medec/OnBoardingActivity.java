package com.example.medec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLinearLayout;
    private SliderAdapter mSliderAdapter;

    private TextView[] mBottomDots;//array to store the dots
    private Button mNextButton;

    private int mCurrentPage;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setContentView(R.layout.activity_on_boarding);


        mSlideViewPager = (ViewPager) findViewById(R.id.viewPager);
        mDotsLinearLayout =(LinearLayout) findViewById(R.id.dotslinearLayout);


        mSliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(mSliderAdapter); //adding our adapter to our viewPager

        addBottomDots(0);
        mSlideViewPager.addOnPageChangeListener(viewPagerListener);

        mNextButton = (Button) findViewById(R.id.next_button);
        //OnClickListeners
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mCurrentPage == mBottomDots.length - 1){
                    Intent  intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    mSlideViewPager.setCurrentItem(mCurrentPage + 1);
                }
            }
        });



    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(OnBoardingActivity.this, MainActivity.class));
        finish();
    }
    //count the number of items you will need
    public void addBottomDots(int currentPosition){
        mBottomDots = new TextView[mSliderAdapter.slides_headings.length];//gets the number of slides
        mDotsLinearLayout.removeAllViews();//prevents creation of so many dots

        for (int i = 0; i < mBottomDots.length; i++){
            mBottomDots[i] = new TextView(this);
            mBottomDots[i].setText(Html.fromHtml("&#8226")); //these are dots
            mBottomDots[i].setTextSize(35);
            mBottomDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            mDotsLinearLayout.addView(mBottomDots[i]);
        }

        if (mBottomDots.length > 0){
            mBottomDots[currentPosition].setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    //to help us know the page we are on
    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            mCurrentPage = position;

            if (position == mBottomDots.length - 1) {
                mNextButton.setText("GET STARTED");
            } else {
                mNextButton.setText("NEXT");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
