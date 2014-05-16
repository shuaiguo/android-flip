package com.wandoujia.huntforapps.fragments;

import android.app.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

import com.wandoujia.huntforapps.R;

/**
 * Created by bear on 5/16/14.
 */
public class Page4Fragment extends Fragment{
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_4, container, false);
    ButterKnife.inject(this, root);
    return root;
  }


  @Override
  public void onResume() {
    super.onResume();
    getActivity().setTitle(R.string.page4_title); 
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.reset(this);
  }
}
