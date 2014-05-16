package com.wandoujia.huntforapps.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wandoujia.huntforapps.R;

import butterknife.ButterKnife;

/**
 * Created by nengxiangzhou on 14-5-15.
 */
public class Page2Fragment extends Fragment {

  public Page2Fragment() {}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_2, container, false);
    root.findViewById(R.id.view_tools);
    ButterKnife.inject(this, root);
    return root;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
