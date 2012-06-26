package net.sareweb.android.dBizi.activity;

import java.util.Locale;

import net.sareweb.android.dBizi.R;
import net.sareweb.android.dBizi.util.DBiziConstants;
import net.sareweb.android.dBizi.util.LangUtil;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
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
				.setIndicator("List",
						res.getDrawable(android.R.drawable.ic_menu_view))
				.setContent(StationListActivity_.intent(this).get());
		tabHost.addTab(spec);

		Intent mapIntent = new Intent().setClass(this, StationMapActivity.class);
		spec = tabHost.newTabSpec("map")
				.setIndicator("Map", res.getDrawable(android.R.drawable.ic_dialog_map))
				.setContent(mapIntent);
		tabHost.addTab(spec);
		
		spec = tabHost
				.newTabSpec("settings")
				.setIndicator(getString(R.string.settings),
						res.getDrawable(android.R.drawable.ic_menu_preferences))
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
			tabHost.getTabWidget()
					.getChildAt(i)
					.setBackgroundColor(
							Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_NO_SELECTED));
		}
		tabHost.getTabWidget()
				.getChildAt(tabHost.getCurrentTab())
				.setBackgroundColor(
						Color.parseColor(DBiziConstants.STYLE_COLOR_TAB_SELECTED));
	}

	TabHost tabHost;
	SharedPreferences userPrefs;

}
