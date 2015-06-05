package com.kodbiro.kbimageviewsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        KBImageView kbImageView = (KBImageView)findViewById(R.id.kbimageview);
//        kbImageView.setImageViewShape(KBImageView.ImageViewShape.CIRCLE);
        kbImageView.setSizeToFit(true);
        kbImageView.setBorderVisible(true);
        kbImageView.setBorderColor(Color.RED);
        kbImageView.setBorderWidth(5.0f);

*/
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Slide1Fragment();
                case 1:
                    return new Slide2Fragment();
                case 2:
                    return new Slide3Fragment();
                case 3:
                    return new Slide4Fragment();
                case 4:
                    return new Slide5Fragment();
                default:
                    return new Slide1Fragment();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


}
