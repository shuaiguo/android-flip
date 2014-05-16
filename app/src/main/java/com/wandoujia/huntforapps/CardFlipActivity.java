/*
 * Copyright 2012 The Android Open Source Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wandoujia.huntforapps;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wandoujia.huntforapps.fragments.Page4Fragment;
import com.wandoujia.huntforapps.fragments.Page5Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates a "card-flip" animation using custom fragment transactions (
 * {@link android.app.FragmentTransaction#setCustomAnimations(int, int)}).
 * 
 * <p>
 * This sample shows an "info" action bar button that shows the back of a "card", rotating the front
 * of the card out and the back of the card in. The reverse animation is played when the user
 * presses the system Back button or the "photo" action bar button.
 * </p>
 */
public class CardFlipActivity extends Activity
    implements FragmentManager.OnBackStackChangedListener {
  /**
   * Whether or not we're showing the back of the card (otherwise showing the front).
   */
  private boolean mShowingBack = false;
  private GestureDetector gestureDetector;
  private List<Fragment> fragments;
  private int currentPosition = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_flip);

    fragments = new ArrayList<Fragment>();
    fragments.add(new Page4Fragment());
    fragments.add(new Page5Fragment());
    fragments.add(new CardFrontFragment());
    fragments.add(new CardBackFragment());

    if (savedInstanceState == null) {
      getFragmentManager()
          .beginTransaction()
          .add(R.id.container, fragments.get(currentPosition))
          .commit();
    } else {
      mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    getFragmentManager().addOnBackStackChangedListener(this);

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    // Add either a "photo" or "finish" button to the action bar, depending on which page
    // is currently selected.
    MenuItem item =
        menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
            mShowingBack
                ? R.string.action_photo
                : R.string.action_info);
    item.setIcon(mShowingBack
        ? R.drawable.ic_action_photo
        : R.drawable.ic_action_info);
    item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    return true;
  }

  private void flipCard() {
    if (mShowingBack) {
      getFragmentManager().popBackStack();
      return;
    }

    // Flip to the back.

    mShowingBack = true;

    currentPosition = currentPosition + 1 == fragments.size() ?  0 : currentPosition + 1;

    // Create and commit a new fragment transaction that adds the fragment for the back of
    // the card, uses custom animations, and is part of the fragment manager's back stack.

    getFragmentManager()
        .beginTransaction()

        // Replace the default fragment animations with animator resources representing
        // rotations when switching to the back of the card, as well as animator
        // resources representing rotations when flipping back to the front (e.g. when
        // the system Back button is pressed).
        .setCustomAnimations(
            R.animator.card_flip_right_in,
            R.animator.card_flip_right_out,
            R.animator.card_flip_left_in,
            R.animator.card_flip_left_out)

        // Replace any fragments currently in the container view with a fragment
        // representing the next page (indicated by the just-incremented currentPage
        // variable).
        .replace(R.id.container, fragments.get(currentPosition))

        // Add this transaction to the back stack, allowing users to press Back
        // to get to the front of the card.
        .addToBackStack(null)

        // Commit the transaction.
        .commit();

  }

  @Override
  public void onBackStackChanged() {
    mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

    // When the back stack changes, invalidate the options menu (action bar).
    invalidateOptionsMenu();
  }

  /**
   * A fragment representing the front of the card.
   */
  public static class CardFrontFragment extends Fragment {
    public CardFrontFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_card_front,
          container, false);
    }
  }

  /**
   * A fragment representing the back of the card.
   */
  public static class CardBackFragment extends Fragment {
    public CardBackFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_card_back,
          container, false);
    }
  }
}
