package com.kodbiro.kbimageviewsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kodbiro.kbimageview.KBImageView;

/**
 * Created by Luka Mijatovic on 05/06/15.
 */
public class Slide5Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_5, null, false);
        KBImageView kbImageView1 = (KBImageView)view.findViewById(R.id.kbimageview1);
        kbImageView1.setSizeToFit(true);
        KBImageView kbImageView2 = (KBImageView) view.findViewById(R.id.kbimageview2);
        kbImageView2.setSizeToFit(true);
        KBImageView kbImageView3 = (KBImageView) view.findViewById(R.id.kbimageview3);
        kbImageView3.setSizeToFit(true);
        KBImageView kbImageView4 = (KBImageView) view.findViewById(R.id.kbimageview4);
        kbImageView4.setSizeToFit(true);

        kbImageView1.setImageViewShape(KBImageView.ImageViewShape.CIRCLE);
        kbImageView2.setImageViewShape(KBImageView.ImageViewShape.CIRCLE);
        kbImageView3.setImageViewShape(KBImageView.ImageViewShape.CIRCLE);
        kbImageView4.setImageViewShape(KBImageView.ImageViewShape.CIRCLE);

        kbImageView2.setBorderColor(Color.RED);
        kbImageView2.setBorderWidth(6.0f);
        kbImageView2.setBorderVisible(true);

        kbImageView3.setShadowRadius(6.0f);
        kbImageView3.setShadowColor(Color.RED);
        kbImageView3.setShadowVisible(true);

        kbImageView4.setBorderVisible(true);
        kbImageView4.setShadowVisible(true);
        kbImageView4.setBorderWidth(6.0f);
        kbImageView4.setBorderColor(Color.WHITE);
        kbImageView4.setShadowColor(Color.BLACK);

        return view;
    }
}
