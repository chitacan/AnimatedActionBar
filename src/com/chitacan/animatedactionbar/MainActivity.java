package com.chitacan.animatedactionbar;

import com.chitacan.animatedactionbar.NotifyingScrollView.OnScrollChangedListener;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.view.Menu;
import android.widget.ScrollView;

public class MainActivity extends Activity {
	
	private Drawable mActionBarBackgroundDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mActionBarBackgroundDrawable = getResources().getDrawable(R.drawable.ab_background);
		mActionBarBackgroundDrawable.setAlpha(0);
		
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
			mActionBarBackgroundDrawable.setCallback(mDrawableCallback);
		
		getActionBar().setBackgroundDrawable(mActionBarBackgroundDrawable);
		
		NotifyingScrollView view = (NotifyingScrollView) findViewById(R.id.scroll_view);
		view.setOnScrollChangedListener(mOnScrollChangedListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnScrollChangedListener mOnScrollChangedListener = new OnScrollChangedListener() {
		
		@Override
		public void onScrollchanged(ScrollView view, int l, int t, int oldl,
				int oldt) {
			final int headerHeight = findViewById(R.id.image_header).getHeight() - getActionBar().getHeight();
			final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
			final int newAlpha = (int) (ratio * 255);
			mActionBarBackgroundDrawable.setAlpha(newAlpha);
		}
	};
	
	private Callback mDrawableCallback = new Callback() {
		
		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
		}
		
		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
		}
		
		@Override
		public void invalidateDrawable(Drawable who) {
			getActionBar().setBackgroundDrawable(who);
		}
	};
}
