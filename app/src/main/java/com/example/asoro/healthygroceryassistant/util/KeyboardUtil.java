package com.example.asoro.healthygroceryassistant.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.Space;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * Keyboard util with helper classes to manipulate the keyboard
 */
public final class KeyboardUtil {

	private KeyboardUtil() {
	}

	/**
	 * Try to hide/close the keyboard for the specified activity
	 *
	 * @param activity
	 * 		the {@link Activity}
	 */
	public static void hideKeyboard(Activity activity) {
		if (activity != null) {
			InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			View decorView = activity.getWindow().getDecorView();
			if (decorView != null) {
				if (imm.isAcceptingText()) {
					imm.hideSoftInputFromWindow(decorView.getWindowToken(), 0);
				}
			}
		}
	}

	/**
	 * Try to show the keyboard for the specified view
	 *
	 * @param view
	 * 		the {@link View}
	 */
	public static void showKeyboard(View view) {
		if (view != null) {
			Context context = view.getContext();
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		}
	}

	/**
	 * Removed the added space
	 *
	 * @param keyBoardSpace
	 * 		the keyboard space view
	 */
	public static void removeKeyboardspace(Space keyBoardSpace) {
		keyBoardSpace.setVisibility(View.GONE);
	}

	/**
	 * Adds an extra space to push up the view if edit text has focus
	 *
	 * @param editText
	 * 		the edit text
	 * @param keyBoardSpace
	 * 		the spacer view
	 */
	public static void addKeyboardSpacer(EditText editText, final Space keyBoardSpace) {
		editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if (hasFocus) {
					keyBoardSpace.setVisibility(View.VISIBLE);
				} else {
					keyBoardSpace.setVisibility(View.GONE);
				}
			}
		});
		editText.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (view.hasFocus()) {
					keyBoardSpace.setVisibility(View.VISIBLE);
				}
				return false;
			}
		});
	}
}
