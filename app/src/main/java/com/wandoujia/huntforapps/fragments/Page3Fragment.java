package com.wandoujia.huntforapps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wandoujia.huntforapps.R;

import butterknife.ButterKnife;

/**
 * Created by limy
 */
public class Page3Fragment extends Fragment{


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_3, container, false);
    ButterKnife.inject(this, root);
    return root;
  }

}
