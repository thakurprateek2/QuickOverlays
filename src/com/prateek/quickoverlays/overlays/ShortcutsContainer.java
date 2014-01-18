package com.prateek.quickoverlays.overlays;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.listeners.OnSizeCahngedListner;
import wei.mark.standout.ui.Window;
import wei.mark.standout.ui.Window.Editor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.prateek.quickoverlays.R;

public class ShortcutsContainer extends StandOutWindow {

	private int oldPosX, oldPosY;
	private int xpos = StandOutLayoutParams.RIGHT,
			ypos = StandOutLayoutParams.RIGHT;
	private int fullFledgedWidth;
	private int fullFledgedHeight;
	public static final String TAG = "Overlays";
	private boolean isStretchedFully = false;

	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return "My shortcuts container";
	}

	@Override
	public int getAppIcon() {
		// TODO Auto-generated method stub
		return R.drawable.ic_launcher;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		DisplayMetrics metrics = ShortcutsContainer.this
				.getResources().getDisplayMetrics();
		 fullFledgedWidth = metrics.widthPixels;
		 fullFledgedHeight = metrics.heightPixels;
	}

	@Override
	public boolean onTouchBody(int id, Window window, View view,
			MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			
			window.edit().setSize(fullFledgedWidth, fullFledgedHeight).setPosition(0, 0)
					.commit();

		} else if (event.getAction() == MotionEvent.ACTION_UP) {

			window.edit().setPosition(oldPosX, oldPosY).setSize(50, 300)
					.commit();
		}else if(event.getAction() == MotionEvent.ACTION_MOVE && isStretchedFully()){
			Log.d(TAG, "co ordinates = x = " + event.getX() + " " + event.getXPrecision() + " y = " + event.getY() + " " + event.getYPrecision());
		}
		
		return true;

//		return super.onTouchBody(id, window, view, event);
	}

	@Override
	public boolean onShow(int id, Window window) {
		// TODO Auto-generated method stub

		Editor editor = window.edit();

		oldPosX = (int) (editor.getmParams().x + 50 * editor.getAnchorX());
		oldPosY = (int) (editor.getmParams().y + 300 * editor.getAnchorY());
		
		window.setOnSizeCahngedListner(new OnSizeCahngedListner() {
			
			@Override
			public void onSizeChangedListner(Window window, int w, int h, int oldw,
					int oldh) {
				if(w==fullFledgedWidth && h == fullFledgedHeight){
					Log.d(TAG,"stretched fully");
					setStretchedFully(true);
				}
				else{
					setStretchedFully(false);
				}
//				Log.d(TAG, " h = " + h + " w = " + w + " oldh = " + oldh  + " oldw = " + oldw  + " bottom = " + window.getBottom() + " right = " + window.getRight());
			}
		});

		return super.onShow(id, window);
	}

	@Override
	public void createAndAttachView(final int id, FrameLayout frame) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.shortcuts_container, frame, true);
	}

	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub

		return new StandOutLayoutParams(id, 50, 300, xpos, ypos);

	}

	@Override
	public int getFlags(int id) {
		// TODO Auto-generated method stub
		// | StandOutFlags.FLAG_DECORATION_SYSTEM
		//| StandOutFlags.FLAG_BODY_MOVE_ENABLE
		return super.getFlags(id) 
				| StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE
				| StandOutFlags.FLAG_WINDOW_HIDE_ENABLE;
	}

	public boolean isStretchedFully() {
		return isStretchedFully;
	}

	public void setStretchedFully(boolean isStretchedFully) {
		this.isStretchedFully = isStretchedFully;
	}

}
