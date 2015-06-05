package com.kodbiro.kbimageviewsample;

import android.graphics.BitmapFactory;
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
public class Slide3Fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_3, null, false);
        KBImageView kbImageView1 = (KBImageView)view.findViewById(R.id.kbimageview);
        KBImageView kbImageView2 = (KBImageView)view.findViewById(R.id.kbimageview2);

        kbImageView1.setSizeToFit(true);
        kbImageView2.setSizeToFit(true);

        kbImageView1.setBorderColor(Color.RED);
        kbImageView1.setBorderVisible(true);
        kbImageView1.setBorderWidth(10.0f);

        kbImageView2.setBorderColor(Color.RED);
        kbImageView2.setBorderVisible(true);
        kbImageView2.setBorderWidth(32.0f);
        kbImageView2.setBorderTexture(BitmapFactory.decodeResource(getResources(), R.drawable.texture));
        return view;
    }
}
