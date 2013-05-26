package com.chitacan.animatedactionbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class NotifyingScrollView extends ScrollView {
	
	public interface OnScrollChangedListener {
		void onScrollchanged(ScrollView view, int l, int t, int oldl, int oldt);
	}
	
	private boolean mIsOverScrollEnabled = true;
	
	private OnScrollChangedListener mOnScrollChangedListener;

	public NotifyingScrollView(Context context) {
		super(context);
	}

	public NotifyingScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public NotifyingScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mOnScrollChangedListener != null)
			mOnScrollChangedListener.onScrollchanged(this, l, t, oldl, oldt);
	}
	
	public void setOnScrollChangedListener(OnScrollChangedListener listener) {
		mOnScrollChangedListener = listener;
	}
	
	public void setOverScrollEnabled(boolean enabled) {
		mIsOverScrollEnabled = enabled;
	}
	
	public boolean isOverScrollEnabled() {
		return mIsOverScrollEnabled;
	}
	@Override
	protected boolean overScrollBy(
			int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		return super.overScrollBy(
				deltaX, 
				deltaY, 
				scrollX, 
				scrollY, 
				scrollRangeX,
				scrollRangeY, 
				mIsOverScrollEnabled ? maxOverScrollX : 0, 
				mIsOverScrollEnabled ? maxOverScrollY : 0, 
				isTouchEvent);
	}
}
