package com.prateek.quickoverlays.overlays;

import wei.mark.standout.StandOutWindow;
import wei.mark.standout.constants.StandOutFlags;
import wei.mark.standout.ui.Window;
import wei.mark.standout.ui.Window.Editor;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

import com.prateek.quickoverlays.R;

public class ShortcutsContainer extends StandOutWindow{
	
	private int oldPosX, oldPosY;
	private int xpos = StandOutLayoutParams.RIGHT, ypos = StandOutLayoutParams.RIGHT;

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
	public boolean onTouchBody(int id, Window window, View view,
			MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchBody(id, window, view, event);
	}
	
	

	@Override
	public boolean onShow(int id, Window window) {
		// TODO Auto-generated method stub
		
		Editor editor = window.edit();
		
		oldPosX = (int) (editor.getmParams().x + 50 * editor.getAnchorX());
		oldPosY = (int) (editor.getmParams().y + 300 * editor.getAnchorY());
		
		return super.onShow(id, window);
	}
	
	
	
	@Override
	public void createAndAttachView(final int id, FrameLayout frame) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.shortcuts_container, frame, true);
		v.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Window mWin = getWindow(id);
				
//				StandOutLayoutParams params = getParams(id, mWin);
				if(event.getAction() == MotionEvent.ACTION_DOWN){
				DisplayMetrics metrics = ShortcutsContainer.this.getResources().getDisplayMetrics();
				int width = metrics.widthPixels;
				int height = metrics.heightPixels;
//				xpos = StandOutLayoutParams.CENTER;
//				ypos = StandOutLayoutParams.CENTER;
//				mWin.originalParams= getParams(id, mWin); 
				
//				params = getParams(id, mWin);
//				mWin.touchInfo.ratio = (float) mWin.originalParams.width / mWin.originalParams.height;
//				mWindowManager.updateViewLayout(mWin, getParams(id, mWin));
				mWin.edit().setSize(width, height).setPosition(0, 0).commit();
//				mWin.edit().setPosition(0, 0).commit();
				}else if(event.getAction() == MotionEvent.ACTION_UP){
//					xpos = StandOutLayoutParams.RIGHT;
//					ypos = StandOutLayoutParams.RIGHT;
//					params = getParams(id, mWin);
//					mWin.originalParams= getParams(id, mWin); 
//					mWin.touchInfo.ratio = (float) mWin.originalParams.width / mWin.originalParams.height;
//					mWindowManager.updateViewLayout(mWin, getParams(id, mWin));
					mWin.edit().setPosition(oldPosX,oldPosY).setSize(50, 300).commit();
//					mWin.edit().setPosition(0, 0).commit();
//					mWin.edit().setPosition(oldPosX,oldPosY).commit();
				}
				
//				getWindow(id).invalidate();
//				getFocusedWindow().edit().setSize(width, height);
				
				return true;
			}
		});
		
	}

	
	
	
	@Override
	public StandOutLayoutParams getParams(int id, Window window) {
		// TODO Auto-generated method stub
		
		
		return new StandOutLayoutParams(id, 50, 300,
				xpos, ypos);
		
	}
	
	@Override
	public int getFlags(int id) {
		// TODO Auto-generated method stub
//		| StandOutFlags.FLAG_DECORATION_SYSTEM
		return super.getFlags(id) 
				| StandOutFlags.FLAG_BODY_MOVE_ENABLE
				| StandOutFlags.FLAG_WINDOW_FOCUSABLE_DISABLE| StandOutFlags.FLAG_WINDOW_HIDE_ENABLE;
	}

}
