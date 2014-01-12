package com.prateek.quickoverlays;

import com.prateek.quickoverlays.overlays.ShortcutsContainer;

import wei.mark.standout.StandOutWindow;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        StandOutWindow
		.show(this, ShortcutsContainer.class, StandOutWindow.DEFAULT_ID);
        
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
