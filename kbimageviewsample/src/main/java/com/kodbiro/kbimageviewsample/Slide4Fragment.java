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
public class Slide4Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_4, null, false);
        KBImageView kbImageView = (KBImageView)view.findViewById(R.id.kbimageview);
        kbImageView.setSizeToFit(true);
        kbImageView.setShadowColor(Color.GREEN);
        kbImageView.setShadowVisible(true);
        kbImageView.setShadowRadius(10.0f);
        return view;
    }
}
