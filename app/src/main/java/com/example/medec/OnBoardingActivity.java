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

    private SliderAdapter mSliderAdapter;
    private LinearLayout mDotsLinearLayout;
    private TextView[] mBottomDots;
    private Button mNextButton;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mNextButton = (Button) findViewById(R.id.next_button);


        mDotsLinearLayout =(LinearLayout) findViewById(R.id.dotslinearLayout);
        mSlideViewPager = (ViewPager) findViewById(R.id.viewPager);

        mSliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(mSliderAdapter);

        addBottomDots(0);
        mSlideViewPager.addOnPageChangeListener(viewPagerListener);

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

    ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            mCurrentPage = position;

            if (position == mBottomDots.length - 1) {
                mNextButton.setText("GOT IT");
            } else {
                mNextButton.setText("NEXT");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
