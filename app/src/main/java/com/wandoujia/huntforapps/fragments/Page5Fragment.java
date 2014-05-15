package com.wandoujia.huntforapps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wandoujia.huntforapps.R;

import butterknife.ButterKnife;

/**
 * Created by zhangnan on 5/15/14.
 */
public class Page5Fragment extends Fragment{
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_5, container, false);
    ButterKnife.inject(this, root);
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
