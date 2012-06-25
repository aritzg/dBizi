package net.sareweb.android.dBizi.activity;


import net.sareweb.android.dBizi.R;
import android.app.Activity;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
public class AboutActivity extends Activity {
	
    private static String TAG = "AboutActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

}

