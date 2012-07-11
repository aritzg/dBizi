package net.sareweb.android.dBizi.activity;

import net.sareweb.android.dBizi.R;
import net.sareweb.android.dBizi.util.DBiziConstants;
import net.sareweb.android.dBizi.util.LangUtil;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;

@EActivity
@OptionsMenu(value = R.menu.menu)
public class DBiziMainActivity extends TabActivity implements
		OnTabChangeListener {

	private static String TAG = "dBizi";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		userPrefs = getSharedPreferences(DBiziConstants.USER_PREFS, MODE_PRIVATE);
		
		LangUtil.updateLanguage(this, userPrefs.getString(DBiziConstants.USER_PREFS_LANG, DBiziConstants.USER_PREF_LANG_EU));
		
		setContentView(R.layout.main);

		Resources res = getResources();
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		TabHost.TabSpec spec;

		spec = tabHost
				.newTabSpec("list")
				.setIndicator(getString(R.string.list),
						res.getDrawable(R.drawable.list))
				.setContent(StationListActivity_.intent(this).get());
		tabHost.addTab(spec);

		Intent mapIntent = new Intent().setClass(this, StationMapActivity.class);
		spec = tabHost.newTabSpec("map")
				.setIndicator(getString(R.string.map), res.getDrawable(R.drawable.world))
				.setContent(mapIntent);
		tabHost.addTab(spec);
		
		spec = tabHost
				.newTabSpec("settings")
				.setIndicator(getString(R.string.settings),
						res.getDrawable(R.drawable.settings))
				.setContent(SettingsActivity_.intent(this).get());
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
		paintTabsColors();

	}

	@Override
	public void onTabChanged(String arg0) {
		paintTabsColors();
	}

	private void paintTabsColors() {
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			View tab = tabHost.getTabWidget().getChildAt(i); 
			
			tab.setBackgroundColor(
							Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_NO_SELECTED));
			
			TextView tv = (TextView) tab.findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_NO_SELECTED_TEXT));
		
		
		}
		View tab = tabHost.getTabWidget()
				.getChildAt(tabHost.getCurrentTab());
		
		tab.setBackgroundColor(
						Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_SELECTED));
		
		TextView tv = (TextView) tab.findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_SELECTED_TEXT));
	}
	
	@Click(R.id.imgReload)
	public void reloladClicked(){
	
		Intent intent = DBiziMainActivity_.intent(this).get();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);

	}
	
	@OptionsItem(R.id.about)
	void about() {
		AboutActivity_.intent(this).start();
	}

	TabHost tabHost;
	SharedPreferences userPrefs;

}
