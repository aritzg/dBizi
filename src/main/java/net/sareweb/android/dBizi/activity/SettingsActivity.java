package net.sareweb.android.dBizi.activity;


import net.sareweb.android.dBizi.R;
import net.sareweb.android.dBizi.util.DBiziConstants;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class SettingsActivity extends Activity implements OnClickListener{
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        userPrefs = getSharedPreferences(DBiziConstants.USER_PREFS, MODE_PRIVATE);
        String lang = userPrefs.getString(DBiziConstants.USER_PREFS_LANG, DBiziConstants.USER_PREF_LANG_EU);

        if(lang.equals(DBiziConstants.USER_PREF_LANG_ES)){
        	rdLangCas.setChecked(true);
        }
        else{
        	rdLangEus.setChecked(true);
        }
        
        rdLangCas.setOnClickListener(this);
        rdLangEus.setOnClickListener(this);
        
        
    }
    
    @Override
	public void onClick(View v) {
    	if(userPrefs==null)userPrefs = getSharedPreferences(DBiziConstants.USER_PREFS, MODE_PRIVATE);
    	editor = userPrefs.edit();
    	
    	switch (v.getId()) {
		case R.id.rdLangCas:
			editor.putString(DBiziConstants.USER_PREFS_LANG, DBiziConstants.USER_PREF_LANG_ES);
			break;

		default:
			editor.putString(DBiziConstants.USER_PREFS_LANG, DBiziConstants.USER_PREF_LANG_EU);
			break;
		}
    	editor.commit();
	}
    
    @ViewById
    RadioButton rdLangCas;
    @ViewById
    RadioButton rdLangEus;
    
    private SharedPreferences.Editor editor;
    private SharedPreferences userPrefs;
    private static String TAG = "SettingsActivity";
	
	

}

