package com.example.medec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;//used for adding the context from wherever it is
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    //arrays for storing our slider values
    //array for images
    public int[] slides_images = {
            R.drawable.onboarding1,
            R.drawable.onboarding2,
            R.drawable.onboarding3
    };

    //array for headings
    public int[] slides_headings = {
            R.string.sliderHeading1,
            R.string.sliderHeading2,
            R.string.sliderHeading3
    };
    //array for descriptions
    public int[] slider_descriptions={
            R.string.sliderDescription1,
            R.string.sliderDescription2,
            R.string.sliderDescription3

    };


    @Override
    public int getCount() {
        return slides_headings.length; //returns the number of slides via the headings
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object; //assigning the view to main object
    }

    @NonNull
    @Override //required to add the slides effects and inflate the adapter
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false); //inflating the view with the slide layout

        //initializing the image and text view in our slideLayout
        ImageView slideImageView = (ImageView)view.findViewById(R.id.sliderimageView);
        TextView slideHeading = (TextView)view.findViewById(R.id.heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.description);


        //setting values
        slideImageView.setImageResource(slides_images[position]);
        slideHeading.setText(slides_headings[position]);
        slideDescription.setText(slider_descriptions[position]);

        container.addView(view);
        return view;
    }


    @Override //once on the last page, it stops the slider preventing errors
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
