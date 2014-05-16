package com.wandoujia.huntforapps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.wandoujia.huntforapps.fragments.Page3Fragment;
import com.wandoujia.huntforapps.fragments.Page2Fragment;
import com.wandoujia.huntforapps.fragments.Page5Fragment;
import com.wandoujia.huntforapps.fragments.Page6Fragment;

import java.util.ArrayList;
import java.util.List;

public class CardFlipActivity extends Activity {
  private GestureDetector gestureDetector;
  private List<Fragment> fragments;
  private int currentPosition = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_flip);

    fragments = new ArrayList<Fragment>();
    fragments.add(new Page2Fragment());
    fragments.add(new Page3Fragment());
    fragments.add(new Page5Fragment());
    fragments.add(new Page6Fragment());

    getFragmentManager()
        .beginTransaction()
        .add(R.id.container, fragments.get(currentPosition))
        .commit();

    gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
      @Override
      public boolean onDown(MotionEvent e) {
        return false;
      }

      @Override
      public void onShowPress(MotionEvent e) {

      }

      @Override
      public boolean onSingleTapUp(MotionEvent e) {
        return false;
      }

      @Override
      public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
      }

      @Override
      public void onLongPress(MotionEvent e) {

      }

      @Override
      public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        flipCard();
        return false;
      }
    });
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    gestureDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  private void flipCard() {
    currentPosition = currentPosition + 1 == fragments.size() ? 0 : currentPosition + 1;

    getFragmentManager()
        .beginTransaction()
        .setCustomAnimations(
            R.animator.card_flip_right_in,
            R.animator.card_flip_right_out,
            R.animator.card_flip_left_in,
            R.animator.card_flip_left_out)
        .replace(R.id.container, fragments.get(currentPosition))
        .commit();
  }

}
